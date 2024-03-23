package com.sjs.jsvill.controller.calendar;

import com.sjs.jsvill.dto.CalendarDTO;
import com.sjs.jsvill.dto.member.MemberDTO;
import com.sjs.jsvill.service.calendar.CalendarService;
import com.sjs.jsvill.service.kafka.ReminderMessage;
import com.sjs.jsvill.service.kafka.ProdReminderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/calendar")
@Slf4j
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
            //start가 7일 이내라면, 카프카에게 메세지 보내기
            ReminderMessage reminderMessage = new ReminderMessage(memberDTO.getMemberRowid(), memberDTO.getPhoneNumber() , calendarDTO.getTitle(), (int)daysBetween);
            this.producer.sendToProducer(reminderMessage, false);
        }
        calendarService.register(calendarDTO);
    }

    @PatchMapping("/{calendarRowid}")
    public ResponseEntity<String> modifyCalendar(@PathVariable("calendarRowid") Long calendarRowid, @Valid @RequestBody CalendarDTO calendarDTO) {
        log.info("Modifying calendar with ID: {}", calendarRowid);

        try {
            calendarService.modify(calendarDTO);
            return new ResponseEntity<>("Calendar modified successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.error("Failed to modify calendar, ID not found: {}", calendarRowid, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error modifying calendar with ID: {}", calendarRowid, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/remove")
    @ResponseBody
    public void modify(Long id, Boolean isAllDelete) {
        //id는 calendarRowid or bundleId
        System.out.println("calendar - remove id : " + id);
        calendarService.remove(id, isAllDelete);
    }
}
