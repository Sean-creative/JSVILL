package com.sjs.jsvill.service.unit;

import com.sjs.jsvill.dto.*;
import com.sjs.jsvill.entity.*;
import com.sjs.jsvill.repository.*;
import com.sjs.jsvill.service.car.CarService;
import com.sjs.jsvill.service.contract.ContractService;
import com.sjs.jsvill.service.option.OptionService;
import com.sjs.jsvill.service.tenant.TenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입 -> repository가 자동 주입
public class UnitServiceImpl implements UnitService {

    private final GroupRepository groupRepository;
    private final UnitRepository unitRepository; //반드시 final로 선언
    private final ContractRepository contractRepository;
    private final OptionRepository optionRepository;
    private final TenantRepository tenantRepository;
    private final CarRepository carRepository;

    private final ContractService contractService;
    private final CarService carService;
    private final TenantService tenantService;
    private final OptionService optionService;


    @Override
    public Long register(UnitDTO dto) {
        log.info("DTO-------------" );
        log.info(dto);
        Unit unit = dtoToEntity(dto);
        unitRepository.save(unit);
        return unit.getUnit_rowid();
    }

    @Override
    @Transactional
    public UnitDTO getWithContractList(Long unit_rowid) {
        Unit unit = unitRepository.getById(unit_rowid);
        List<ContractDTO> contractDTOList;

        //기간이 지나지 않은 계약들을 가져온다. 현재꺼+미래꺼
        List<Contract> contarctList =  contractRepository.findContarctByUnitNotOld(unit_rowid);
        for (Contract contract : contarctList) {
            Long contractRowid = contract.getContract_rowid();
            List<Option> optionList = optionRepository.findByContract(contractRowid);
            //옵션이 하나도 없다면? 이걸 쿼리로 했어야 하는데, 괜히 끌고 들어와서 고생이네
            //TODO 옵션이 하나도 없으면 어떻게 하지?
            Option option = null;
            if(!optionList.isEmpty()) option = optionList.get(0);

            List<Tenant> tenantList = tenantRepository.findByContract(contractRowid);
            List<Car> carList = carRepository.findByCar(contractRowid);

            contarctList.forEach(i -> System.out.println("contract : " + i));
            System.out.println("option : " + option);
            tenantList.forEach(i -> System.out.println("tenant : " + i));
            carList.forEach(i -> System.out.println("car : " + i));


            List<CarDTO> carDTOList = carService.entitiesToDTO(carList);
            List<TenantDTO> tenantDTOList = tenantService.entitiesToDTO(tenantList);
            OptionDTO optionDTo = optionService.entityToDTO(option);

            //TODO ContractDTOList 만들기,
            contractService.entityToDTO(contract, carDTOList, tenantDTOList, optionDTo);

        }


//        UnitDTO unitDTO = entityToDTOWithContract(unit, contarctList,optionList);

        ///미래 계약중인 계약 하나 가져와서 똑같이 1.입주자 가져오고 2.계약일 가져오고!


        UnitDTO unitDTO = entityToDTO(unit);
        return unitDTO;
    }

    @Override
    public void test() {
        log.info(1);
        optionService.entityToDTO(new Option());
        log.info(2);
    }


//    @Override
//    public PageResultDTO<GroupDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
//        log.info(pageRequestDTO);
//
//        Function<Object[], GroupDTO> fn = (en -> entityToDTO((Group)en[0]));
//
//        Page<Object[]> result = repository.getGroupWithAll(pageRequestDTO.getPageable(Sort.by("group_rowid").descending()));
//        
//        return
//    }
}
