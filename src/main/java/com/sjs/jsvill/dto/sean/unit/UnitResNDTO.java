package com.sjs.jsvill.dto.sean.unit;

import com.sjs.jsvill.dto.sean.ContractDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UnitResNDTO {
    private Long unitRowid; //수정, 삭제
    private Long groupRowid; //건물 관리로 이동
    private String addr2; //101호
    private String memo; //비고

    private String groupTitle;//건물이름
    private String groupAddr;//건물주소

    //계약리스트
    @Builder.Default
    private List<ContractDTO> contractDTOList = new ArrayList<>();
}
