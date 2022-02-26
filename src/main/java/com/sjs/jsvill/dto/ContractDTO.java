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
    private Long contractRowid;
    private Long unitRowid;
    private Long _contractTypeRowid;
    private String startDate;
    private String endDate;
    private Long deposit; //보증금
    private Long rentFee; //월세
    private Long managementFees; //관리비
    private Long paymentDay; //납부일

    private List<TenantDTO> tenantDTOList = new ArrayList<>();
    private List<CarDTO> carDTOList = new ArrayList<>();
    private OptionDTO optionDTO = new OptionDTO();
}
