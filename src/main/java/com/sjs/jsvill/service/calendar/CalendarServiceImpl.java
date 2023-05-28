package com.sjs.jsvill.service.calendar;

import com.sjs.jsvill.dto.CalendarDTO;
import com.sjs.jsvill.entity.Calendar;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.repository.CalendarRepository;
import com.sjs.jsvill.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입 -> repository가 자동 주입
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository; //반드시 final로 선언

    @Override
    public List<CalendarDTO> getList(Long groupRowid) {
        List<Calendar> calendarList = calendarRepository.findAllByGroup(Group.builder().group_rowid(groupRowid).build());
        return CalendarDTO.entityToDTOList(calendarList);
    }

    @Override
    public void register(CalendarDTO calendarDTO) {
        log.info("calendarDTO-register: " + calendarDTO);

        List<Calendar> calendarList = dtoToEntities(calendarDTO);
        Json.stringToJson(calendarList, "service-register");

        //loopDays 루프로 등록
        calendarRepository.saveAll(calendarList);
    }

    @Override
    public void remove(Long id, Boolean isAllDelete) {
        log.info("id remove : " + id);
        if (isAllDelete) calendarRepository.deleteByBundleId(id);
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

        Calendar calendar = calendarRepository.getById(calendarDTO.getCalendarRowid());
        calendar.setTitle(calendarDTO.getTitle());
        calendar.setDescription(calendarDTO.getDescription());
        calendar.setStart(calendarDTO.getStart());
        calendar.setEnd(calendarDTO.getEnd());
        calendar.setRepetition(calendarDTO.getRepetition());
        calendar.setRepetitionEnd(calendarDTO.getRepetitionEnd());
        calendar.setBackgroundcolor(calendarDTO.getBackgroundColor());
        calendar.setTextcolor(calendarDTO.getTextColor());
        calendar.setIsallday(calendarDTO.isIsallday());

        switch (calendarDTO.getTypeNo()) {
            case 1 -> {calendarRepository.save(calendar);}
            case 2 -> {
                // 2. 원래 반복일정 x - 수정 후 반복일정이 o -> 해당 bundleId로 추가(bundleId가 1개임)
                // 2.1 원래 있던 일정하나 삭제하고, list로 다시 만들기
                remove(calendar.getCalendar_rowid(), true);
                register(calendarDTO);
            }
            case 3 -> {}
            case 4 -> {}
            default -> throw new IllegalStateException("Unexpected value: " + calendarDTO.getTypeNo());
        }

        calendarRepository.save(calendar);
    }
}
