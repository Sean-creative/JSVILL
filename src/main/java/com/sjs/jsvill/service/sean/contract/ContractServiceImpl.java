package com.sjs.jsvill.service.sean.contract;

import com.sjs.jsvill.common.UserDuplicateCheck;
import com.sjs.jsvill.dto.sean.CarDTO;
import com.sjs.jsvill.dto.sean.ContractDTO;
import com.sjs.jsvill.dto.sean.OptionDTO;
import com.sjs.jsvill.dto.sean.TenantDTO;
import com.sjs.jsvill.entity.sean.*;
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
    private final CarRepository carRepository;
    private final ContractTenantRepository contractTenantRepository;

    @Override
    public String phoneCheck(List<UserDuplicateCheck> duplicateCheckList) {
        //1. tenant를 전부 가져온다
        //2. 이름과 폰번호가 중복 or 중복된게 없으면 -> 정상적인 플로우 -> "" 리턴
        //3. 번호만 중복인데 이름이 다르면 오류! -> 폰번호 리턴
        String result = "";
        List<Tenant> tenantList = tenantRepository.findAll();
        System.out.println("duplicateCheckList : " + duplicateCheckList);
        System.out.println("tenantList : " + tenantList);

        for (UserDuplicateCheck userDuplicateCheck : duplicateCheckList) {
            System.out.println("userDuplicateCheck: " + userDuplicateCheck);
            for (Tenant tenant : tenantList) {
                if (userDuplicateCheck.phone.equals(tenant.getPhone())&&!userDuplicateCheck.title.equals(tenant.getTitle()))
                     result = userDuplicateCheck.phone;
            }
        }
        System.out.println("result : "+result);
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
        Option option = OptionDTO.DTOToEntity(optionDTO, contract.getContract_rowid());
        if (!option.getOptionList().isBlank()) {
            log.info("optionRepository.save(option)" + optionRepository.save(option));
        }

//        2. 세입자 등록
        //세입자 등록할 때 이미 해당 핸드폰 번호가 등록되어있을 수도있음 ex)기존에 살았던 세입자
        List<Tenant> tenantList = TenantDTO.DTOToEntities(contractDTO.getTenantDTOList());
        for (Tenant tenant : tenantList) {
            final String PHONE = tenant.getPhone();
            //세입자를 등록하기전에, 폰번호로 검사를해서 또 등록하는것을 막야아한다.
            if (!tenantRepository.existsByPhone(PHONE)) tenantRepository.save(tenant);
            //세입자를 등록했든 안했든 -> 폰번호로 세입자를 다시 찾아서 tenantRowid를 알아내야한다.
            Tenant tenantFromDB = tenantRepository.findByPhone(PHONE);
            //계약 세입자 매핑 테이블에 등록
            contractTenantRepository.save(ContractTenant.builder().contract(contract).tenant(tenantFromDB).build());

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

        return Contract.entityToDTO(contract.get(), carDTOList, Tenant.entitiesToDTO(tenantList), Option.entityToDTO(option));
    }
    @Override
    @Transactional
    public Contract get(Long contractRowid) {
        return contractRepository.getById(contractRowid);
    }
}