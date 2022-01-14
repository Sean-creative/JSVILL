package com.sjs.jsvill.service;

import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Unit;

public interface UnitService {
    Long register(UnitDTO dto);

//    PageResultDTO<UnitDTO, Object[]>getList(PageRequestDTO pageRequestDTO); //목록처리

    //파라미터로 받는건 DTO인데 -> DB에 접근하는 데이터는 엔티티로 바꿔줘야함
    default Unit dtoToEntity(UnitDTO dto) {
        Group group = Group.builder().group_rowid(dto.getGroup_rowid()).build();
        Unit unit = Unit.builder()
                .unit_rowid(dto.getUnit_rowid())
                .group(group)
                .addr2(dto.getAddr2())
                .build();
        return unit;
    }

    default UnitDTO entityToDTO(Unit unit) {
        UnitDTO unitDTO = UnitDTO.builder()
                .unit_rowid(unit.getUnit_rowid())
                .group_rowid(unit.getGroup().getGroup_rowid())
                .addr2(unit.getAddr2())
                .build();
        return unitDTO;
    }
}
