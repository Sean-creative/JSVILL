package com.sjs.jsvill.service.sean.contract;

import com.sjs.jsvill.common.UserDuplicateCheck;
import com.sjs.jsvill.dto.sean.CarDTO;
import com.sjs.jsvill.dto.sean.ContractDTO;
import com.sjs.jsvill.dto.sean.OptionDTO;
import com.sjs.jsvill.dto.sean.TenantDTO;
import com.sjs.jsvill.entity.sean.*;
import com.sjs.jsvill.entity.sub._LivingType;
import com.sjs.jsvill.repository.sean.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final TenantRepository tenantRepository;
    private final ContractRepository contractRepository;
    private final OptionRepository optionRepository;
    private final OptionLogRepository optionLogRepository;
    private final CarRepository carRepository;
    private final ContractTenantRepository contractTenantRepository;
    private final ContractTenantLogRepository contractTenantLogRepository;

    @Override
    public String phoneCheck(List<UserDuplicateCheck> duplicateCheckList) {
        //1. tenant를 전부 가져온다
        //2. 이름과 폰번호가 중복 or 중복된게 없으면 -> 정상적인 플로우 -> "" 리턴
        //3. 번호만 중복인데 이름이 다르면 오류! -> 폰번호 리턴
        String result = "";
        List<Tenant> tenantList = tenantRepository.findAll();
//        System.out.println("duplicateCheckList : " + duplicateCheckList);
//        System.out.println("tenantList : " + tenantList);

        for (UserDuplicateCheck userDuplicateCheck : duplicateCheckList) {
//            System.out.println("userDuplicateCheck: " + userDuplicateCheck);
            for (Tenant tenant : tenantList) {
                if (userDuplicateCheck.phone.equals(tenant.getPhone())&&!userDuplicateCheck.title.equals(tenant.getTitle()))
                     result = userDuplicateCheck.phone;
            }
        }
//        System.out.println("result : "+result);
        return result;
    }

    @Override
    @Transactional
    //계약은 트랜잭션으로 이루어져 한다. 뭐 하나라도 잘못되면 롤백!!
    public void register(ContractDTO contractDTO) {
//        1. 계약 등록
        Contract contract = contractRepository.save(dtoToEntity(contractDTO));
//        1-1. 계약R -> 옵션 등록
        log.info("contract.getContract_rowid() : " + contract.getContract_rowid());
        OptionDTO optionDTO = contractDTO.getOptionDTO();
        Option option = Option.DTOToEntity(optionDTO, contract.getContract_rowid());
        if (!option.getOptionList().isBlank()) {
            //이름은 옵션리스트지만, 저장 할 때는 String으로 저장함
            log.info("optionRepository.save(option)" + optionRepository.save(option));
            optionLogRepository.save(OptionLog.optionToOptionLog(option));
        }

//        2. 세입자 등록
        //세입자 등록할 때 이미 해당 핸드폰 번호가 등록되어있을 수도있음 ex)기존에 살았던 세입자
        List<Tenant> tenantList = TenantDTO.DTOToEntities(contractDTO.getTenantDTOList());
        for (Tenant tenant : tenantList) {
            final String PHONE = tenant.getPhone();
            //세입자를 등록하기전에, 폰번호로 검사를해서 또 등록하는것을 막야아한다.
            if (!tenantRepository.existsByPhone(PHONE)) tenantRepository.save(tenant);
            //새로등록한 세입자든 기존에 있던 세입자든 -> 폰번호로 세입자를 다시 찾아서 tenantRowid를 알아내야한다.
            Tenant tenantFromDB = tenantRepository.findByPhone(PHONE);
            //계약 세입자 매핑 테이블에 등록
            ContractTenant contractTenant = ContractTenant.builder().contract(contract).tenant(tenantFromDB).build();
            contractTenantRepository.save(contractTenant);
            //거주 하는지 안하는지 어떻게 알지?? -> 계약 시작일 기준으로 계산을 해봅시다!
            _LivingType livingType = _LivingType.isStartedContract(contractDTO.getStartdate());
            contractTenantLogRepository.save(ContractTenantLog.ContractTenantToContractTenantLog(contractTenant, livingType));
        }
    }

    @Override
    @Transactional
    public void remove(Long contract_rowid) {
        contractRepository.deleteById(contract_rowid);
    }

    @Override
    public ContractDTO getDTO(Long contractRowid) {
        //1. 계약 정보
        Optional<Contract> contract = contractRepository.findById(contractRowid);
        //2. 옵션 정보
        Option option = optionRepository.findByContract(contract.get());
        //3. 세입자 정보
        List<Tenant> tenantList = new ArrayList<>();
        List<CarDTO> carDTOList = new ArrayList<>();
        List<ContractTenant> result = contractTenantRepository.findAllByContract(contract.get());
        result.forEach(contractTenant -> {
            tenantList.add(contractTenant.getTenant());
            //4. 차량정보
            carRepository.findAllByTenant(contractTenant.getTenant()).forEach(i -> {
                carDTOList.add(Car.entityToDTO(i, contractTenant.getTenant().getPhone()));
            });
        });
        tenantList.sort((a, b) -> Boolean.compare(b.getIscontractor(), a.getIscontractor()));
        return Contract.entityToDTO(contract.get(), carDTOList, Tenant.entitiesToDTO(tenantList), OptionDTO.entityToDTO(option));
    }
    @Override
    @Transactional
    public Contract get(Long contractRowid) {
        return contractRepository.getById(contractRowid);
    }

    @Transactional
    @Override
    public void modify(ContractDTO contractDTO) {
        Contract contract = contractRepository.getById(contractDTO.getContractRowid());
        Option option = optionRepository.findByContract(Contract.builder().contract_rowid(contractDTO.getContractRowid()).build());

        if(contract != null) {
            contract.changeStartDate(contractDTO.getStartdate());
            contract.changeEndDate(contractDTO.getEnddate());
            contract.changeDeposit(contractDTO.getDeposit());
            contract.changeRentfee(contractDTO.getRentFee());
            contract.changeManagemnetfees(contractDTO.getManagementFees());
            contract.changePaymentday(contractDTO.getPaymentDay());
            System.out.println("contractDTO.getContractRowid() : " + contractDTO.getContractRowid());
            System.out.println("contract : " + contract.getContract_rowid());
            contractRepository.save(contract);
        } /*else throw new Exception("modify 오류 발생");*/
        if(option != null) {
            option.changeOptionList(Option.listToCsv(contractDTO.getOptionDTO().getOptionList()));
            optionRepository.save(option);
            optionLogRepository.save(OptionLog.optionToOptionLog(option));
        }
        else {
            option = Option.DTOToEntity(contractDTO.getOptionDTO(), contract.getContract_rowid());
            if (!option.getOptionList().isBlank()) {
                //이름은 옵션리스트지만, 저장 할 때는 String으로 저장함
                log.info("optionRepository.save(option)" + optionRepository.save(option));
                optionLogRepository.save(OptionLog.optionToOptionLog(option));
            }
        }

        //Tenant, TenantContarct, TenantContarctLog
        contractDTO.getTenantDTOList().forEach(tenantDTO -> {
            Tenant tenant = tenantRepository.findByPhone(tenantDTO.getPhone());

            if(tenant==null) { //등록이 안된 사람이라면 -> 새로 등록!
                //1. Tenant에 등록을 해주고
                //2. TenantContarct, TenantContarctLog에 등록을 해준다.
                tenant = tenantRepository.save(TenantDTO.DTOToEntitiy(tenantDTO));
                ContractTenant contractTenant = ContractTenant.builder().contract(contract).tenant(tenant).build();
                contractTenantRepository.save(contractTenant);
                _LivingType livingType = _LivingType.isStartedContract(contractDTO.getStartdate());
                contractTenantLogRepository.save(ContractTenantLog.ContractTenantToContractTenantLog(contractTenant, livingType));
            }
            else { // 기존에 등록된 사람이라면 -> 수정
                //1. 해당 Tenant에서 변경된 값으로 수정해준다.
                tenant.changeIsContractor(tenantDTO.getIsContractor());
                tenant.changePhone(tenantDTO.getPhone());
                tenant.changeLivingType(_LivingType.builder()._livingtype_rowid(tenantDTO.getLivingType()).build());
                tenant.changeTilte(tenantDTO.getTitle());
                tenantRepository.save(tenant);
            }
            tenantDTO.setTenantRowid(tenant.getTenant_rowid());
        });

        //1. 기존에 해당 contractRowid로 부터 contractTenant 리스트를 가져온다.
        List<ContractTenant> contractTenantList = contractTenantRepository.findAllByContract(contract);
        //여기에는 tenantRowid가 여러개 들어있음
        System.out.println("contractTenantList : " + contractTenantList);
        List<TenantDTO> tenantDTOList = contractDTO.getTenantDTOList();
        contractTenantList.forEach(contractTenant -> {
            boolean noneMatch = tenantDTOList.stream().noneMatch(tenantDTO ->
                    tenantDTO.getTenantRowid()==contractTenant.getTenant().getTenant_rowid());
            //일치하는게 하나도 없다면
            if(noneMatch) {
                contractTenantRepository.delete(contractTenant);
                _LivingType livingType = _LivingType.builder()._livingtype_rowid(10L).build();
                contractTenantLogRepository.save(ContractTenantLog.ContractTenantToContractTenantLog(contractTenant, livingType));

            }
        });
    }
}