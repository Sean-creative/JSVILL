package com.sjs.jsvill.service.unit;

import com.sjs.jsvill.dto.ContractDTO;
import com.sjs.jsvill.dto.OptionDTO;
import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Option;
import com.sjs.jsvill.entity.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface UnitService {
    Long register(UnitDTO dto);
    UnitDTO get(Long unit_rowid);

//    PageResultDTO<UnitDTO, Object[]>getList(PageRequestDTO pageRequestDTO); //목록처리

    //파라미터로 받는건 DTO인데 -> DB에 접근하는 데이터는 엔티티로 바꿔줘야함
    default Unit dtoToEntity(UnitDTO dto) {
        Group group = Group.builder().group_rowid(dto.getGroup_rowid()).build();
        Unit unit = Unit.builder()
                .unit_rowid(dto.getUnit_rowid())
                .group(group)
                .addr2(dto.getAddr2().replace("호", "")+"호")
                .deposit(dto.getDeposit())
                .rentfee(dto.getRentfee())
                .managementfees(dto.getManagementfees())
                .paymentday(dto.getPaymentday())
                .memo(dto.getMemo())
                .build();
        return unit;
    }

    default UnitDTO entityToDTO(Unit unit) {
        UnitDTO unitDTO = UnitDTO.builder()
                .unit_rowid(unit.getUnit_rowid())
                .group_rowid(unit.getGroup().getGroup_rowid())
                .addr2(unit.getAddr2())
                .deposit(unit.getDeposit())
                .rentfee(unit.getRentfee())
                .managementfees(unit.getManagementfees())
                .paymentday(unit.getPaymentday())
                .memo(unit.getMemo())
                .build();
        return unitDTO;
    }

    default UnitDTO entityToDTOWithContract(Unit unit, List<Contract> contractList, List<Option> optionList) {
        List<ContractDTO> contractDTOList = new ArrayList<>();
        List<OptionDTO> optionDTOList = new ArrayList<>();
        Group group = unit.getGroup();
        if(!contractList.isEmpty()) {
            contractDTOList = contractList.stream().map(contract -> ContractDTO.builder()
                    .unit_rowid(contract.getUnit().getUnit_rowid())
                    ._contracttype_rowid(contract.getContractType().get_contracttype_rowid())
                    .title(contract.getTitle())
                    .startdate(contract.getStartdate())
                    .enddate(contract.getEnddate())
                    .isprogressing(contract.getIsprogressing())
                    .deposit(contract.getDeposit())
                    .rentfee(contract.getRentfee())
                    .managementfees(contract.getManagementfees())
                    .paymentday(contract.getPaymentday())
                    .build()
            ).collect(Collectors.toList());
        }
        if(!optionList.isEmpty()) {
            optionDTOList = optionList.stream().map(option -> OptionDTO.builder()
                    .option_rowid(option.getOption_rowid())
                    .contract_rowid(option.getContract().getContract_rowid())
                    .title(option.getTitle())
                    .build()
            ).collect(Collectors.toList());
        }

        UnitDTO unitDTO = UnitDTO.builder()
                .unit_rowid(unit.getUnit_rowid())
                .group_rowid(unit.getGroup().getGroup_rowid())
                .addr2(unit.getAddr2())
                .deposit(unit.getDeposit())
                .rentfee(unit.getRentfee())
                .managementfees(unit.getManagementfees())
                .paymentday(unit.getPaymentday())
                .memo(unit.getMemo())
                .groupTitle(group.getTitle())
                .groupAddr(group.getAddr1())
                .contractDTOList(contractDTOList)
                .optionDTOList(optionDTOList)
                .build();
        return unitDTO;
    }
}
