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

    @GetMapping("/register")
    @ResponseBody
    public String register(CalendarDTO calendarDTO) {
        return calendarService.register(calendarDTO).toString();
    }

    @GetMapping("/read")
    @ResponseBody
    public List<CalendarDTO> read(@RequestParam("groupRowid") Long groupRowid) {
        return calendarService.getList(groupRowid);
    }
}
