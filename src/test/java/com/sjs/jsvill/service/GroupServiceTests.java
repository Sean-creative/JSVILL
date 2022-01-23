package com.sjs.jsvill.service;

import com.sjs.jsvill.dto.GroupDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GroupServiceTests {

    @Autowired
    private GroupService groupService;

    @Test
    public void testRegister() {
        GroupDTO groupDTO = GroupDTO.builder()
                .groupType_rowid(5L)
                .title("title")
                .addr1("addr1")
                .postnum("postnum")
                .memo("moemo")
                .build();
        System.out.println(groupService.register(groupDTO));
    }

//    @Test
//    public void getList(){
//        List<GroupDTO> objArr =  groupService.getList(1L);
//        List<Group> groupList = (List<Group>) objArr[0];
//        Map<Long, List<Unit>> groupMap = (Map<Long, List<Unit>>) objArr[1];
//        for(Group group : groupList) {
//            System.out.println("group: " + group);
//            System.out.println(groupMap.get(group.getGroup_rowid()));
//        }
//    }
    @Test
    public void getList(){
        List<GroupDTO> groupDTOList =  groupService.getList(1L);
        for(GroupDTO groupDTO : groupDTOList) {
            System.out.println("groupDTO: " + groupDTO.toString());
        }
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
