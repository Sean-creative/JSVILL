package com.sjs.jsvill.service;

import com.sjs.jsvill.dto.GroupDTO;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity._GroupType;
import com.sjs.jsvill.service.group.GroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GroupServiceTests {

    @Autowired
    private GroupService service;

    @Test
    public void testRegister() {
        GroupDTO groupDTO = GroupDTO.builder()
                .member(Member.builder().member_rowid(1L).build())
                .groupType(_GroupType.builder()._grouptype_rowid(10L).build())
                .title("title")
                .addr1("addr1")
                .postnum("postnum")
                .memo("moemo")
                .build();
        System.out.println(service.register(groupDTO));
    }
//    @Test
//    public void testList(){
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
//        PageResultDTO<GroupDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);
//
//        System.out.println("PREV: " + resultDTO.isPrev());
//        System.out.println("NEXT: " + resultDTO.isNext());
//        System.out.println("TOTAL: " + resultDTO.getTotalPage());
//
//        System.out.println("-------------------------------------------------------------");
//        for (GroupDTO groupDTO : resultDTO.getDtoList()) {
//            System.out.println(groupDTO);
//        }
//
//        System.out.println("===========================================================");
//        resultDTO.getPageList().forEach(i -> System.out.println(i));
//    }
//
//    @Test
//    public void testSearch(){
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//                .page(1)
//                .size(10)
//                .type("tc") //검색 조건 t, c, w, tc, tcw....
//                .keyword("한글") //검색 키워드
//                .build();
//
//        PageResultDTO<GroupDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);
//
//        System.out.println("PREV: " + resultDTO.isPrev());
//        System.out.println("NEXT: " + resultDTO.isNext());
//        System.out.println("TOTAL: " + resultDTO.getTotalPage());
//
//        System.out.println("--------------------------------------------------");
//        for (GroupDTO groupDTO : resultDTO.getDtoList()) {
//            System.out.println(groupDTO);
//        }
//        System.out.println("===================================================");
//        resultDTO.getPageList().forEach(i -> System.out.println(i));
//    }
}
