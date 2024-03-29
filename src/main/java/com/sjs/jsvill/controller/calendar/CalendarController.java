package com.sjs.jsvill.controller.calendar;

import com.sjs.jsvill.dto.CalendarDTO;
import com.sjs.jsvill.dto.member.MemberDTO;
import com.sjs.jsvill.service.calendar.CalendarService;
import com.sjs.jsvill.service.kafka.ReminderMessage;
import com.sjs.jsvill.service.kafka.ProdReminderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/calendar")
@Log4j2
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;
    private final ProdReminderService producer;

    @GetMapping("/read")
    @ResponseBody
    public List<CalendarDTO> read(@RequestParam("groupRowid") Long groupRowid) {
        return calendarService.getList(groupRowid);
    }

    @PostMapping("/register")
    @ResponseBody
    public void register(@AuthenticationPrincipal MemberDTO memberDTO, CalendarDTO calendarDTO) {
        //LocalDate.now -> 현재 날짜(연, 월, 일)만을 반환
        LocalDate paramStartDate = LocalDate.parse(calendarDTO.getStart().substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), paramStartDate);
        log.info("daysBetween :{}", daysBetween);
        if(daysBetween<7) {
            //start가 오늘이라면, 카프카에게 메세지 보내기
            //start가 7일 이내라면, 카프카에게 메세지 보내기
            ReminderMessage reminderMessage = new ReminderMessage(memberDTO.getMemberRowid(), memberDTO.getPhoneNumber() , calendarDTO.getTitle(), 0);
            this.producer.sendToProducer(reminderMessage, false);
        }
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
    public void modify(Long id, Boolean isAllDelete) {
        //id는 calendarRowid or bundleId
        System.out.println("calendar - remove id : " + id);
        calendarService.remove(id, isAllDelete);
    }
}
