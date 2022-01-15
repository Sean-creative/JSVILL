package com.sjs.jsvill.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UnitDTO {
    private Long unit_rowid;
    private Long group_rowid;
    private String addr2;
    private Long deposit;
    private Long rentfee;
    private Long managementfees;
    private Long paymentday;
}
