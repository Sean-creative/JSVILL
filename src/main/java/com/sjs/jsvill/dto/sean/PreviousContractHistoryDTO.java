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
public class PreviousContractHistoryDTO {
    private Long unitRowid;
    private String groupTitle; //명일동 JSVILL
    private String addr2; //101호
    //TODO List는 항상 new 해줘야하나?
    private List<PreviousContract> contractList = new ArrayList<>();

    @Data
    public static class PreviousContract {
        private Long contractRowid;
        private String startdate; //view에서 name을 startDate로 하게되면 value값이 제대로 들어가지 않아서 이렇게 처리함
        private String enddate;
        private String tenantTitle; //세대주
    }
}
