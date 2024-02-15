package com.sjs.jsvill.service;

import com.sjs.jsvill.dto.CalendarDTO;
import com.sjs.jsvill.service.calendar.CalendarService;
import com.sjs.jsvill.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CalendarServiceTests {

    @Autowired
    private CalendarService calendarService;

    @Test
    public void getList() {
        List<CalendarDTO> list =  calendarService.getList(3L);
        list.forEach(i-> Json.stringToJson(i, "CalendarServiceTests-getList"));
    }
    @Test
    public void findEventsWithinNextWeek() {
        List<CalendarDTO> list =  calendarService.findEventsWithinNextWeek();
        list.forEach(i-> Json.stringToJson(i, "CalendarServiceTests-findEventsWithinNextWeek"));
    }

   /*
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
    */
}
