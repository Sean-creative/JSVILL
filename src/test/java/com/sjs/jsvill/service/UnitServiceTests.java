package com.sjs.jsvill.service;

import com.sjs.jsvill.dto.sean.UnitDTO;
import com.sjs.jsvill.entity.sean.Unit;
import com.sjs.jsvill.service.sean.unit.UnitService;
import com.sjs.jsvill.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
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
                    .detailAddr("detailAddr" + i)
                    .memo("memo" + i)
                    .build();
            System.out.println(unitService.register(unitDTO));
        });
    }

    @Test
    public void getWithContractList() {
        Json.stringToJson(unitService.getWithContractList(14L), "test-getWithContractList");
    }

    @Test
    @Commit
    public void delete() {
    }

    @Test
    @Transactional
    public void get() {
        Unit unit = unitService.get(14L);
        System.out.println("unit : " + unit);
        System.out.println("unit.getGroup() : " + unit.getGroup());
    }

    @Test
    public void modify() {

        Unit unit = unitService.get(1L);
        System.out.println("Before unit : " + unit);

        unit.changedetailAddr("주소 바꿨다!");
//        unit.changeDeposit(999L);
        UnitDTO unitDTO = UnitDTO.entityToDTO(unit);
        System.out.println("unitDTO : " + unitDTO);

        unitService.modify(unitDTO);
        unit = unitService.get(1L);
        System.out.println("After unit : " + unit);
    }
}
