package com.sjs.jsvill.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupManageResDTO {

    private class Group {
        private String title; //명일동 제이에스빌[빌라]
        private String addr1; //서울 강동구 명일동 173번지
        private String yearOfCompletion; //준공년 - 2020년 12월
        private String tenantCnt; //15명의 입주자
        private String titleDeposit; //보증금 합계 : 18억 5000만원
        private String titleMonthly; //월세 합계 : 매달 0만원
        private String totalManagementfees; //관리비 합계 매달 : 50만원
    }

    //총 건물 개수
    private int groupCnt;
    private List<Group> groupList;

//    public groupManageDTO(Page<EN> result, Function<EN, DTO> fn){
//        dtoList = result.stream().map(fn).collect(Collectors.toList());
//        totalPage = result.getTotalPages();
//        makePageList(result.getPageable());
//    }
//
//    private void makePageList(Pageable pageable){
//        this.page = pageable.getPageNumber() + 1; //0부터 시작하므로 1을 추가
//        this.size = pageable.getPageSize();
//
//        //temp end page
//        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;
//        start = tempEnd - 9;
//        prev = start > 1;
//        end = totalPage > tempEnd ? tempEnd: totalPage;
//        next = totalPage > tempEnd;
//        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
//    }



}
