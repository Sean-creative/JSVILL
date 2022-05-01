package com.sjs.jsvill.dto.sean;

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
    private Long groupRowid;
    private Long groupTypeRowid;
    private String title;
    private String addr1;
    private String postNum;
    private String memo;
    private String completionDate;

    @Builder.Default
    private Long totalDeposit = 0L; //총보증금
    @Builder.Default
    private Long totalRentFee = 0L; //금액(월세)
    @Builder.Default
    private Long totalManagementFees = 0L; //관리비
    @Builder.Default
    private Long totalTenantCnt = 0L; //입주자 총 수

    @Builder.Default
    private List<UnitDTO> unitDTOList = new ArrayList<>();
}
