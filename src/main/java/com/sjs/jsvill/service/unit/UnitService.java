package com.sjs.jsvill.service.unit;

import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Unit;

public interface UnitService {
    Long register(UnitDTO dto);
    UnitDTO getWithContractList(Long unit_rowid);

    Unit get(Long unit_rowid);
    void modify(UnitDTO unitDTO);
    void remove(Long unit_rowid);

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


}
