package com.sjs.jsvill.service.calendar;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sjs.jsvill.dto.CalendarDTO;
import com.sjs.jsvill.entity.Calendar;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.QCalendar;
import com.sjs.jsvill.repository.CalendarRepository;
import com.sjs.jsvill.service.kafka.NotiMessage;
import com.sjs.jsvill.service.kafka.ProdNotiService;
import com.sjs.jsvill.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입 -> repository가 자동 주입
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository; //반드시 final로 선언
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CalendarDTO> getList(Long groupRowid) {
        List<Calendar> calendarList = calendarRepository.findAllByGroup(Group.builder().group_rowid(groupRowid).build());
        return CalendarDTO.entityToDTOList(calendarList);
    }

    //오늘 날짜를 기준으로 7일 이내에 시작하는 모든 이벤트를 조회
    @Override
    public List<CalendarDTO> findEventsWithinNextWeek() {
        //오늘을 기준으로 일주일전부터 매일 보내주자.
        //오늘 : 2024-02-16
        QCalendar calendar = QCalendar.calendar; // QueryDSL의 Q타입 인스턴스 생성
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(7);

        // JPQL과는 달리, QueryDSL에서는 타입 안전성을 제공하며, 컴파일 타임에 쿼리의 문법 오류를 잡을 수 있습니다.

        return CalendarDTO.entityToDTOList(queryFactory
                .selectFrom(calendar)
                .where(calendar.start.between(
                        today.toString(), // LocalDate를 문자열로 변환
                        nextWeek.toString())) // LocalDate를 문자열로 변환
                .fetch());
    }

    @Override
    public void register(CalendarDTO calendarDTO) {
        log.info("calendarDTO-register: " + calendarDTO);
        List<Calendar> calendarList = Calendar.dtoToEntities(calendarDTO, true);
        Json.stringToJson(calendarList, "service-register");
        //loopDays 루프로 등록
        calendarRepository.saveAll(calendarList);
    }

    @Override
    public void remove(Long id, Boolean isBundleId) {
        log.info("id remove : " + id);
        if (isBundleId) calendarRepository.deleteByBundleId(id);
        else calendarRepository.deleteById(id);
    }

    @Override
    public void modify(CalendarDTO calendarDTO) {
        log.info("calendarDTO modify : " + calendarDTO);
// 1. 원래 반복일정 x - 수정 후 반복일정이 x -> 해당 bundleId로 추가(bundleId가 1개임)
// 2. 원래 반복일정 x - 수정 후 반복일정이 o -> 해당 bundleId로 추가(bundleId가 1개임)
// 3. 원래 반복일정 o - 수정 후 반복일정이 x -> alert 생성 "해당 일정을 제외하고 반복일정은 사라집니다." 삭제버튼 누르면 나머지 반복일정은 삭제됨
// 4. 원래 반복일정 o - 수정 후 반복일정이 o -> 해당 bundleId로 수정
// 4.1 A B C D 중 C에서 반복일정을 수정해도 전체 반복일정이 영향을 받으면 된다. (연대책임으로 ㄱㄱ)

        switch (calendarDTO.getTypeNo()) {
            case 1 ->
                // 1. 원래 반복일정 x - 수정 후 반복일정이 x -> 해당 bundleId로 추가(bundleId가 1개임)
                    calendarRepository.save(Calendar.dtoToEntity(calendarDTO, true));
            case 2 -> {
                // 2. 원래 반복일정 x - 수정 후 반복일정이 o -> 해당 bundleId로 추가(bundleId가 1개임)
                // 2.1 원래 있던 일정하나 삭제하고, list로 다시 만들기
                remove(calendarDTO.getCalendarRowid(), false);
                register(calendarDTO);
            }
            case 3 -> {
                // 3. 원래 반복일정 o - 수정 후 반복일정이 x -> alert 생성 "해당 일정을 제외하고 반복일정은 사라집니다." 삭제버튼 누르면 나머지 반복일정은 삭제됨
                // 3.1 수정할거는 하고 나머지는 삭제해야함 (JPQL로 할것)
                remove(calendarDTO.getBundleId(), true);
                calendarRepository.save(Calendar.dtoToEntity(calendarDTO, false));
            }
            case 4 -> {
                // 4. 원래 반복일정 o - 수정 후 반복일정이 o -> 해당 bundleId로 수정
                // 4.1 A B C D 중 C에서 반복일정을 수정해도 전체 반복일정이 영향을 받으면 된다. (연대책임으로 ㄱㄱ)
                // 4.2 bundleId로 전부 삭제하고 -> 다시 등록하자 (찝찝하긴 하지만..)
                remove(calendarDTO.getBundleId(), true);
                register(calendarDTO);
            }
            default -> throw new IllegalStateException("Unexpected value: " + calendarDTO.getTypeNo());
        }
    }
}
