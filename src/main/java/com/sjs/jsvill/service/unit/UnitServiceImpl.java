package com.sjs.jsvill.service.unit;

import com.sjs.jsvill.dto.*;
import com.sjs.jsvill.entity.*;
import com.sjs.jsvill.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Long register(UnitDTO dto) {
        log.info("DTO-------------");
        log.info(dto);
        Unit unit = dtoToEntity(dto);
        unitRepository.save(unit);
        return unit.getUnit_rowid();
    }

    @Override
    @Transactional
    public UnitDTO getWithContractList(Long unit_rowid) {
        Unit unit = unitRepository.getById(unit_rowid);
        List<ContractDTO> contractDTOList = new ArrayList<>();

        //기간이 지나지 않은 계약들을 가져온다. 현재꺼+미래꺼
        List<Contract> contarctList = contractRepository.findContarctByUnitNotOld(unit_rowid);
        for (Contract contract : contarctList) {
            Long contractRowid = contract.getContract_rowid();
            List<Option> optionList = optionRepository.findByContract(contractRowid);
            List<Tenant> tenantList = tenantRepository.findByContract(contractRowid);
            List<Car> carList = carRepository.findByCar(contractRowid);

            List<CarDTO> carDTOList = Car.entitiesToDTO(carList);
            List<TenantDTO> tenantDTOList = Tenant.entitiesToDTO(tenantList);
            OptionDTO optionDTO = Option.entityToDTO(optionList);

            contractDTOList.add(Contract.entityToDTO(contract, carDTOList, tenantDTOList, optionDTO));
        }
        UnitDTO unitDTO = Unit.entityToDTOWithContract(unit, contractDTOList);
        ///미래 계약중인 계약 하나 가져와서 똑같이 1.입주자 가져오고 2.계약일 가져오고!
        return unitDTO;
    }

    @Override
    public Unit get(Long unitRowid) {
        Optional<Unit> unit = unitRepository.findById(unitRowid);
        return unit.get();
    }

    @Transactional
    @Override
    public void modify(UnitDTO unitDTO) {
        Unit unit = unitRepository.getById(unitDTO.getUnitRowid());

        System.out.println("unitDTO.getAddr2() : " + unitDTO.getAddr2());

        if (unit != null) {
            unit.changeAddr2(unitDTO.getAddr2());
            unit.changeMemo(unitDTO.getMemo());
            unitRepository.save(unit);
        }
    }

    @Override
    public void remove(Long unit_rowid) {
        //unit을 삭제하면 그에 해당하는 계약도 삭제되어야 한다.

        unitRepository.getById(unit_rowid);
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
