package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UnitDTO {
    private Long unitRowid;
    private Long groupRowid;
    private String detailAddr; //101호
    private String memo; //비고

    private String groupTitle;//건물이름
    private String groupAddr;//건물주소


    //계약리스트
    @Builder.Default
    private List<ContractDTO> contractDTOList = new ArrayList<>();


    public static UnitDTO entityToDTO(Unit unit) {
        UnitDTO unitDTO = UnitDTO.builder()
                .unitRowid(unit.getUnit_rowid())
                .groupRowid(unit.getGroup().getGroup_rowid())
                .detailAddr(unit.getDetailaddr())
                .memo(unit.getMemo())
                .build();
        return unitDTO;
    }

    //여기에 재료를 쏟아 부으면 너무 과부하가 걸릴까 걱정 -> 역할을 분리하고자 unit빼고는 DTO를 받는게 낫겠다.
    public static UnitDTO entityToDTOWithContract(Unit unit, List<ContractDTO> contractDTOList) {
        Group group = unit.getGroup();
        UnitDTO unitDTO = UnitDTO.builder()
                .unitRowid(unit.getUnit_rowid())
                .groupRowid(unit.getGroup().getGroup_rowid())
                .detailAddr(unit.getDetailaddr())
                .memo(unit.getMemo())
                .groupTitle(group.getTitle())
                .groupAddr(group.getLandaddr())
                .contractDTOList(contractDTOList)
                .build();
        return unitDTO;
    }
}
