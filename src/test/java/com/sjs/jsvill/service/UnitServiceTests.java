package com.sjs.jsvill.service;

import com.sjs.jsvill.dto.UnitDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class UnitServiceTests {

    @Autowired
    private UnitService unitService;

    @Test
    public void testRegister() {
        IntStream.rangeClosed(1, 3).forEach( i -> {
            UnitDTO unitDTO = UnitDTO.builder()
                    .unit_rowid(Integer.toUnsignedLong(i))
                    .group_rowid(1L)
                    .addr2("addr2" + i)
                    .build();
            System.out.println(unitService.register(unitDTO));
        });
    }
//    @Test
//    public void testList(){
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
//        PageResultDTO<UnitDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);
//
//        System.out.println("PREV: " + resultDTO.isPrev());
//        System.out.println("NEXT: " + resultDTO.isNext());
//        System.out.println("TOTAL: " + resultDTO.getTotalPage());
//
//        System.out.println("-------------------------------------------------------------");
//        for (UnitDTO unitDTO : resultDTO.getDtoList()) {
//            System.out.println(unitDTO);
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
//        PageResultDTO<UnitDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);
//
//        System.out.println("PREV: " + resultDTO.isPrev());
//        System.out.println("NEXT: " + resultDTO.isNext());
//        System.out.println("TOTAL: " + resultDTO.getTotalPage());
//
//        System.out.println("--------------------------------------------------");
//        for (UnitDTO unitDTO : resultDTO.getDtoList()) {
//            System.out.println(unitDTO);
//        }
//        System.out.println("===================================================");
//        resultDTO.getPageList().forEach(i -> System.out.println(i));
//    }
}