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
    private final ContractTenantRepository contractTenantRepository;

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
    public UnitDTO getWithContractList(Long unitRowid) {
        Unit unit = unitRepository.getById(unitRowid);
        List<ContractDTO> contractDTOList = new ArrayList<>();

        //기간이 지나지 않은 계약들을 가져온다. 현재꺼+미래꺼
        List<Contract> contarctList = contractRepository.findContarctByUnitNotOld(unitRowid);
        for (Contract contract : contarctList) {
            Option option = optionRepository.findByContract(contract);
            List<Tenant> tenantList = new ArrayList<>();
            List<Car> carList = new ArrayList<>();
            List<ContractTenant> contractTenantList = contractTenantRepository.findAllByContract(contract);
            contractTenantList.forEach(ContractTenant -> {
                tenantList.add(ContractTenant.getTenant());
                //차량정보
                carList.addAll(carRepository.findAllByTenant(ContractTenant.getTenant()));
            });

            tenantList.sort((a, b) -> Boolean.compare(b.getIscontractor(), a.getIscontractor()));
            contractDTOList.add(ContractDTO.entityToDTO(contract, TenantDTO.entitiesToDTOList(tenantList), CarDTO.entitiesToDTOList(carList), OptionDTO.entityToDTO(option)));
        }
        UnitDTO unitDTO = UnitDTO.entityToDTOWithContract(unit, contractDTOList);
        //미래 계약중인 계약 하나 가져와서 똑같이 1.입주자 가져오고 2.계약일 가져오고!
        return unitDTO;
    }

    @Override
    @Transactional
    public Unit get(Long unitRowid) {
        Unit unit = unitRepository.getById(unitRowid);
        return unit;
    }

    @Transactional
    @Override
    public void modify(UnitDTO unitDTO) {
        Unit unit = unitRepository.getById(unitDTO.getUnitRowid());
        System.out.println("unitDTO.getdetailAddr() : " + unitDTO.getDetailAddr());

        if (unit != null) {
            unit.changedetailAddr(unitDTO.getDetailAddr());
            unit.changeMemo(unitDTO.getMemo());
            unitRepository.save(unit);
        }
    }

    @Override
    public void remove(Long unitRowid) {
        unitRepository.deleteById(unitRowid);
    }
}