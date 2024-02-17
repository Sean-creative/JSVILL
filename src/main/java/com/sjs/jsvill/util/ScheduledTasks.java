package com.sjs.jsvill.util;

import com.sjs.jsvill.dto.CalendarDTO;
import com.sjs.jsvill.repository.GroupMemberRepository;
import com.sjs.jsvill.service.calendar.CalendarService;
import com.sjs.jsvill.service.kafka.NotiMessage;
import com.sjs.jsvill.service.kafka.ProdNotiService;
import com.sjs.jsvill.service.reminder.member.ReminderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduledTasks {
    //Service하고 Repository 둘 다 여기서 선언하는게 레이어 분리가 안되어있긴하다...
    private final CalendarService calendarService;
    private final GroupMemberRepository groupMemberRepository;
    private final ProdNotiService producer;
    private final ReminderService reminderService;

    //@Scheduled(fixedRate = 10000) //10초마다 실행
    //@Scheduled(cron = "0 * * * * *") //1분 마다 실행 (0초 마다 실행이니까)
    //@Scheduled(cron = "0 */5 * * * *") //5분마다 실행
    //@Scheduled(cron = "0 20 17 * * *") //17시 20분마다 실행
    @Scheduled(cron = "0 0 0 * * ?") //매일 00시에 실행
    public void dailyTask() {
        //1. 해당 기간에 맞는 캘린더 일정을 모두 가져옴
        //2. 그룹 멤버도 전체 가져온다음, 하나를 기준으로 잡고 필터링함
        //3. 그렇게 추려진 캘린더 내용을 카프카페에게 메세지르 보냄
        List<CalendarDTO> calendarDTOS = calendarService.findEventsWithinNextWeek();
        groupMemberRepository.findAll().forEach(gm -> {
            reminderService.deleteAllByMember(gm.getMember().getMemberRowid());
            Long nowGroupRowid = gm.getGroup().getGroup_rowid();
            List<CalendarDTO> nowCalendarDTOS = calendarDTOS.stream().filter(i -> i.getGroupRowid().equals(nowGroupRowid)).toList();

            nowCalendarDTOS.forEach(calendarDTO -> {
                LocalDate paramStartDate = LocalDate.parse(calendarDTO.getStart().substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                // 두 날짜 사이의 기간을 계산합니다.
                Period period = Period.between(LocalDate.now(), paramStartDate);
                // 계산된 기간에서 일(days) 수를 가져옵니다.
                int daysBetween = period.getDays();

                NotiMessage notiMessage = new NotiMessage(gm.getMember().getMemberRowid(), gm.getMember().getPhoneNumber(), calendarDTO.getTitle(), daysBetween);
                this.producer.sendToProducer(notiMessage, true);
            });
        });
    }
}
