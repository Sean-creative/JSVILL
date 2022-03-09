package com.sjs.jsvill.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.entity.Unit;
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
                    .memo("memo" + i)
                    .build();
            System.out.println(unitService.register(unitDTO));
        });
    }

    @Test
    public void getWithContractList() {
        UnitDTO unitDTO = unitService.getWithContractList(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String userAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(unitDTO);
            System.out.println("userAsString : " + userAsString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Commit
    public void delete() {
    }

    @Test
    public void get() {
        Unit unit = unitService.get(1L);
        System.out.println("unit : " + unit);
    }

    @Test
    public void modify() {

        Unit unit = unitService.get(1L);
        System.out.println("Before unit : " + unit);

        unit.changeAddr2("주소 바꿨다!");
//        unit.changeDeposit(999L);
        UnitDTO unitDTO = Unit.entityToDTO(unit);
        System.out.println("unitDTO : " + unitDTO);

        unitService.modify(unitDTO);
        unit = unitService.get(1L);
        System.out.println("After unit : " + unit);
    }
}
