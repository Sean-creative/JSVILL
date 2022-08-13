package com.sjs.jsvill.dto.sean.contract;

import com.sjs.jsvill.dto.sean.OptionDTO;
import com.sjs.jsvill.dto.sean.TenantDTO;
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
public class ContractWpDTO {
    private Long contractRowid;
    private Long unitRowid;
    private Long ContractTypeRowid;
    private String startdate; //view에서 name을 startDate로 하게되면 value값이 제대로 들어가지 않아서 이렇게 처리함
    private String enddate;
    private Long deposit; //보증금
    private Long rentFee; //월세
    private Long managementFees; //관리비
    private Long paymentDay; //납부일

    @Builder.Default
    private List<TenantDTO> tenantDTOList = new ArrayList<>();
    @Builder.Default
    private OptionDTO optionDTO = new OptionDTO();
}
