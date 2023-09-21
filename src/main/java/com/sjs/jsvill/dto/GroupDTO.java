package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.sub._GroupType;
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
    private String postNum;
    private String landAddr;
    private String roadAddr;
    private String lat;
    private String lng;
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


    public static Group dtoToEntity(GroupDTO groupDTO) {
        return Group.builder()
                .group_rowid(groupDTO.groupRowid)
                ._grouptype(_GroupType.builder()._grouptype_rowid(groupDTO.groupTypeRowid).build())
                .title(groupDTO.title)
                .landaddr(groupDTO.landAddr)
                .roadaddr(groupDTO.roadAddr)
                .lat(groupDTO.lat)
                .lng(groupDTO.lng)
                .postNum(groupDTO.postNum)
                .memo(groupDTO.memo)
                .completiondate(groupDTO.completionDate)
            .build();
    }
}
