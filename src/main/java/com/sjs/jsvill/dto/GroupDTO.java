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
public class GroupDTO {
    private Long group_rowid;
    private Long groupMember_rowid;
    private Long groupType_rowid;
    private String title;
    private String addr1;
    private String postnum;
    private String memo;
    private String completiondate;

    private Long totalDeposit; //총보증금
    private Long totalRentfee; //금액(월세)
    private Long totalManagementfees; //관리비
    private Long totalTenantCnt; //입주자 총 수

    @Builder.Default
    private List<UnitDTO> unitDTOList = new ArrayList<>();
}
