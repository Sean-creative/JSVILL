package com.sjs.jsvill.service;

import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.service.unit.UnitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.stream.LongStream;

@SpringBootTest
public class UnitServiceTests {

    @Autowired
    private UnitService unitService;

    @Test
    public void testRegister() {
        LongStream.rangeClosed(1, 3).forEach(i -> {
            UnitDTO unitDTO = UnitDTO.builder()
                    .unitRowid(i)
                    .groupRowid(1L)
                    .addr2("addr2" + i)
                    .deposit(i)
                    .rentFee(i)
                    .managementFees(i)
                    .paymentDay(i)
                    .memo("memo" + i)
                    .build();
            System.out.println(unitService.register(unitDTO));
        });
    }

    @Test
    public void get() {
        UnitDTO unitDTO = unitService.getWithContractList(1L);
        System.out.println(unitDTO.toString());
    }


    @Test
    @Commit
    public void delete() {

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
