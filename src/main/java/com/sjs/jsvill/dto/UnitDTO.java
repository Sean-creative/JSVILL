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
    private Long unit_rowid;
    private Long group_rowid;
    private String addr2; //101호
    private Long deposit; //보증금
    private Long rentfee; //월세
    private Long managementfees; //관리비
    private Long paymentday; //납부일
    private String memo; //비고

    private String groupTitle;//건물이름

    //계약리스트
    private List<ContractDTO> contractDTOList = new ArrayList<>();
    //현재 계약의 옵션리스트
    private List<OptionDTO> optionDTOList = new ArrayList<>();



}
