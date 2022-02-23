package com.sjs.jsvill.dto;

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
public class UnitDTO {
    private Long unitRowid;
    private Long groupRowid;
    private String addr2; //101호
    private Long deposit; //보증금
    private Long rentFee; //월세
    private Long managementFees; //관리비
    private Long paymentDay; //납부일
    private String memo; //비고

    private String groupTitle;//건물이름
    private String groupAddr;//건물주소

    //계약리스트
    private List<ContractDTO> contractDTOList = new ArrayList<>();
    //현재 계약의 옵션리스트
    private List<OptionDTO> optionDTOList = new ArrayList<>();
}
