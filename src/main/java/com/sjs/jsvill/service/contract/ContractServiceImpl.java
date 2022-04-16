package com.sjs.jsvill.service.contract;

import com.sjs.jsvill.dto.CarDTO;
import com.sjs.jsvill.dto.ContractDTO;
import com.sjs.jsvill.dto.OptionDTO;
import com.sjs.jsvill.dto.TenantDTO;
import com.sjs.jsvill.entity.*;
import com.sjs.jsvill.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public String phoneCheck(List<String> phoneList) {
        //1. 해당 폰번호가 DB에 있는 검사를 한다
        Tenant tenant = tenantRepository.findAllByPhoneIn(phoneList);
        //2. 중복된 폰번호가 없으면 공백 리턴 / 중복된 폰번호가 있으면 해당 폰번호 리턴
        if (tenant == null) return "";
        else return tenant.getPhone();
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
        log.info("optionDTO : " + optionDTO);
        //Option은 무조건 등록되게 설계! -> 나중에는 있으면 수정되고 없으면 등록되게 바꿀 수도?
        Option option = OptionDTO.DTOToEntity(optionDTO, contract.getContract_rowid());
        log.info("optionRepository.save(option)" + optionRepository.save(option));

//        2. 세입자 등록
        //세입자 등록할 때 이미 해당 핸드폰 번호가 등록되어있을 수도있음 ex)기존에 살았던 세입자
        List<Tenant> tenantList = TenantDTO.DTOToEntities(contractDTO.getTenantDTOList());
        for (Tenant tenant : tenantList) {
            final String PHONE = tenant.getPhone();
            //세입자를 등록하기전에, 폰번호로 검사를해서 또 등록하는것을 막야아한다.
            if(!tenantRepository.existsByPhone(PHONE)) tenantRepository.save(tenant);
            //세입자를 등록했든 안했든 -> 폰번호로 세입자를 다시 찾아서 tenantRowid를 알아내야한다.
            Tenant tenantFromDB = tenantRepository.findByPhone(PHONE);
            //계약 세입자 매핑 테이블에 등록
            contractTenantRepository.save(ContractTenant.builder().contract(contract).tenant(tenantFromDB).build());


//        2-1. 세입자R -> 차량 등록
//        차량리스트에서 세입자의 폰번호랑 같은것을 찾은 후 -> 해당 세입자를 외래키로 등록을 한다.
            log.info("phone : " + PHONE);
            List<CarDTO> carDTOList = contractDTO.getCarDTOList().stream().filter(c -> c.getPhone().equals(PHONE)).collect(Collectors.toList());
            log.info("carDTOList : " + carDTOList);

            //등록할 차량이 없을 수도 있다!! ex) 계약서 등록 할 때 사용자가 차량을 추가 안한경우
            //차량이 이미 DB에 등록되어있을 수도 있음 ex)기존에 살았던 세입자 -> 해당 tenantRowid로 DB에 있는지 검사하기
            for (CarDTO carDTO : carDTOList) {
                if(!carRepository.existsByNumber(carDTO.getNumber())) {
                    log.info("carDTO : " + carDTO);
                    Car car = CarDTO.DTOToEntity(carDTO, tenantFromDB.getTenant_rowid());
                    log.info("carRepository.save(car)" + carRepository.save(car));
                }
            }
        }
    }

    @Override
    @Transactional
    public void remove(Long contract_rowid) {
        //계약을 삭제 하려면 -> contract_tenant와 option을 삭제해야한다.
        Contract contract = Contract.builder().contract_rowid(contract_rowid).build();
        contractTenantRepository.deleteByContract(contract);
        optionRepository.deleteByContract(contract);
        contractRepository.delete(contract);
    }

    @Override
    public ContractDTO get(Long contractRowid) {
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
            carRepository.findAllByTenant(contractTenant.getTenant()).forEach( i -> {
                carDTOList.add(Car.entityToDTO(i, contractTenant.getTenant().getPhone()));
            });

        });
        return Contract.entityToDTO(contract.get(), carDTOList, Tenant.entitiesToDTO(tenantList), Option.entityToDTO(option));
    }
}