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
public class ContractDTO {
    private Long contract_rowid;
    private Long unit_rowid;
    private Long _contracttype_rowid;
    private String title;
    private String startdate;
    private String enddate;
    private Boolean isprogressing;
    private Long deposit; //보증금
    private Long rentfee; //월세
    private Long managementfees; //관리비
    private Long paymentday; //납부일


    private List<TenantDTO> tenantDTOList = new ArrayList<>();
    private List<CarDTO> carDTOList = new ArrayList<>();
}
