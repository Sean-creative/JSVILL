package com.sjs.jsvill.controller.calendar;

import com.sjs.jsvill.dto.CalendarDTO;
import com.sjs.jsvill.service.calendar.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/calendar")
@Log4j2
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping("/read")
    @ResponseBody
    public List<CalendarDTO> read(@RequestParam("groupRowid") Long groupRowid) {
        return calendarService.getList(groupRowid);
    }

    @PostMapping("/register")
    @ResponseBody
    public void register(CalendarDTO calendarDTO) {
        calendarService.register(calendarDTO);
    }

    @PostMapping("/modify")
    @ResponseBody
    public void modify(CalendarDTO calendarDTO) {
        System.out.println("calendar - modify calendarRowid: " + calendarDTO.getCalendarRowid());
        calendarService.modify(calendarDTO);
    }

    @PostMapping("/remove")
    @ResponseBody
    public void modify(Long calendarRowid) {
        System.out.println("calendar - remove calendarRowid : " + calendarRowid);
        calendarService.remove(calendarRowid);
    }
}
