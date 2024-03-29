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
public class PreviousContractHistoryDTO {
    private Long unitRowid;
    private String groupTitle; //명일동 JSVILL
    private String detailAddr; //101호
    private Boolean isAsc;
    //TODO List는 항상 new 해줘야하나?
    @Builder.Default
    private List<PreviousContractDTO> previousContractDTOList = new ArrayList<>();

    @Data
    public static class PreviousContractDTO {
        private Long contractRowid;
        private String startDate; //view에서 name을 startDate로 하게되면 value값이 제대로 들어가지 않아서 이렇게 처리함
        private String endDate;
        private String tenantTitle; //세대주
    }
}
