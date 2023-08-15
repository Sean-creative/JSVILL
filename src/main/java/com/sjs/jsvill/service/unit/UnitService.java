package com.sjs.jsvill.service.unit;

import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.dto.UnitFileDTO;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Unit;

public interface UnitService {
    Long register(UnitDTO unitDTO, UnitFileDTO unitFileDTO);
    UnitDTO getWithContractList(Long unitRowid);

    Unit get(Long unitRowid);
    void modify(UnitDTO unitDTO);
    void remove(Long unitRowid);

    //파라미터로 받는건 DTO인데 -> DB에 접근하는 데이터는 엔티티로 바꿔줘야함
    default Unit dtoToEntity(UnitDTO dto) {
        Group group = Group.builder().group_rowid(dto.getGroupRowid()).build();
        Unit unit = Unit.builder()
                .unit_rowid(dto.getUnitRowid())
                .group(group)
                .detailaddr(dto.getDetailAddr().replace("호", "")+"호")
                .memo(dto.getMemo())
                .build();
        return unit;
    }


}
