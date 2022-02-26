package com.sjs.jsvill.service.unit;

import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Unit;

public interface UnitService {
    Long register(UnitDTO dto);
    UnitDTO getWithContractList(Long unit_rowid);

    void test();

    //파라미터로 받는건 DTO인데 -> DB에 접근하는 데이터는 엔티티로 바꿔줘야함
    default Unit dtoToEntity(UnitDTO dto) {
        Group group = Group.builder().group_rowid(dto.getGroupRowid()).build();
        Unit unit = Unit.builder()
                .unit_rowid(dto.getUnitRowid())
                .group(group)
                .addr2(dto.getAddr2().replace("호", "")+"호")
                .deposit(dto.getDeposit())
                .rentfee(dto.getRentFee())
                .managementfees(dto.getManagementFees())
                .paymentday(dto.getPaymentDay())
                .memo(dto.getMemo())
                .build();
        return unit;
    }

    //단순히 호실만 다룰 때
    default UnitDTO entityToDTO(Unit unit) {
        UnitDTO unitDTO = UnitDTO.builder()
                .unitRowid(unit.getUnit_rowid())
                .groupRowid(unit.getGroup().getGroup_rowid())
                .addr2(unit.getAddr2())
                .deposit(unit.getDeposit())
                .rentFee(unit.getRentfee())
                .managementFees(unit.getManagementfees())
                .paymentDay(unit.getPaymentday())
                .memo(unit.getMemo())
                .build();
        return unitDTO;
    }

    //여기에 재료를 쏟아 부으면 너무 과부하가 걸릴까 걱정 -> 역할을 분리하고자 unit빼고는 DTO를 받는게 낫겠다.
//    default UnitDTO entityToDTOWithContract(Unit unit, List<Contract> contarctList) {
//        List<ContractDTO> contractDTOList = new ArrayList<>();
//        List<OptionDTO> optionDTOList = new ArrayList<>();
//        Group group = unit.getGroup();
//        if(!contarctList.isEmpty()) {
//            contractDTOList = contarctList.stream().map(contract -> ContractDTO.builder()
//                    .tenantRowid(contract.getUnit().getUnit_rowid())
//                    ._contracttypeRowid(contract.getContractType().get_contracttype_rowid())
//                    .title(contract.getTitle())
//                    .startdate(contract.getStartdate())
//                    .enddate(contract.getEnddate())
//                    .isprogressing(contract.getIsprogressing())
//                    .deposit(contract.getDeposit())
//                    .rentfee(contract.getRentfee())
//                    .managementfees(contract.getManagementfees())
//                    .paymentday(contract.getPaymentday())
//                    .build()
//            ).collect(Collectors.toList());
//        }
//        if(!optionList.isEmpty()) {
//            optionDTOList = optionList.stream().map(option -> OptionDTO.builder()
//                    .optionRowid(option.getOption_rowid())
//                    .contract_rowid(option.getContarct().getContract_rowid())
//                    .title(option.getTitle())
//                    .build()
//            ).collect(Collectors.toList());
//        }
//
//        UnitDTO unitDTO = UnitDTO.builder()
//                .unitRowid(unit.getUnit_rowid())
//                .groupRowid(unit.getGroup().getGroup_rowid())
//                .addr2(unit.getAddr2())
//                .deposit(unit.getDeposit())
//                .rentFee(unit.getRentfee())
//                .managementFees(unit.getManagementfees())
//                .paymentDay(unit.getPaymentday())
//                .memo(unit.getMemo())
//                .groupTitle(group.getTitle())
//                .groupAddr(group.getAddr1())
//                .contractDTOList(contractDTOList)
//                .optionDTOList(optionDTOList)
//                .build();
//        return unitDTO;
//    }
}
