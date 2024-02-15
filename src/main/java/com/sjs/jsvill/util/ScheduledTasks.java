package com.sjs.jsvill.util;

import com.sjs.jsvill.dto.CalendarDTO;
import com.sjs.jsvill.dto.NotiMessageDTO;
import com.sjs.jsvill.repository.GroupMemberRepository;
import com.sjs.jsvill.service.calendar.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduledTasks {
    //Service하고 Repository 둘 다 여기서 선언하는게 레이어 분리가 안되어있긴하다...
    private final CalendarService calendarService;
    private final GroupMemberRepository groupMemberRepository;

    @Scheduled(cron = "0 * * * * *")
    public void dailyTask() {
        //1. 해당 기간에 맞는 캘린더 일정을 모두 가져와서
        //2. 그룹 rowid로 그룹화(컬렉션)을 한다음
        //3. 그룹 멤버를 참고해서
        //4. 해당 멤버한테 알림을 보냄


        List<CalendarDTO> calendarDTOS =  calendarService.findEventsWithinNextWeek();
        calendarDTOS.forEach(i -> Json.stringToJson(i, "calendarDTOS-dailyTask"));
        List<NotiMessageDTO> notiMessageDTOS = new ArrayList<>();
        groupMemberRepository.findAll().forEach(gm -> {
            Long nowGroupRowid = gm.getGroup().getGroup_rowid();
            List<CalendarDTO> nowCalendarDTOS = calendarDTOS.stream().filter(i -> i.getGroupRowid().equals(nowGroupRowid)).toList();

            nowCalendarDTOS.forEach(calendarDTO -> {
                notiMessageDTOS.add(NotiMessageDTO.builder().memberRowid(gm.getMember().getMemberRowid()).message(calendarDTO.getTitle()).createdHours("15").build());
            });
        });
        notiMessageDTOS.forEach(i -> Json.stringToJson(i, "notiMessageDTOS-dailyTask"));

        // 달력 테이블 검토 및 사용자에게 알림 보내기 로직 구현
        log.info("매분마다 실행되는 작업");
        // 예: 사용자에게 일정 알림을 보내는 코드
    }

//    @Scheduled(cron = "0 0 0 * * ?")
//    public void dailyTask() {
//        // 달력 테이블 검토 및 사용자에게 알림 보내기 로직 구현
//        System.out.println("매일 00시에 실행되는 작업");
//        // 예: 사용자에게 일정 알림을 보내는 코드
//    }

//    @Scheduled(fixedRate = 10000)
//    public void scheduleTaskWithFixedRate() {
//        System.out.println("10초마다 실행되는 작업: " + System.currentTimeMillis() / 1000);
//    }
}
