package com.sjs.jsvill.service.calendar;

import com.sjs.jsvill.dto.CalendarDTO;
import com.sjs.jsvill.entity.Calendar;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.repository.CalendarRepository;
import com.sjs.jsvill.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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
        log.info("calendarDTO : " + calendarDTO);

        List<Calendar> calendarList = dtoToEntities(calendarDTO);
        Json.stringToJson(calendarList, "service-register");

        //loopDays 루프로 등록
        calendarRepository.saveAll(calendarList);
    }

    @Override
    public void remove(Long id, Boolean isAllDelete) {
        if(isAllDelete) calendarRepository.deleteByBundleid(id);
        else calendarRepository.deleteById(id);
    }

    @Override
    public void modify(CalendarDTO calendarDTO) {
        log.info("calendarDTO modify : " + calendarDTO);

        Calendar calendar = calendarRepository.getById(calendarDTO.getCalendarRowid());

        calendar.setTitle(calendarDTO.getTitle());
        calendar.setDescription(calendarDTO.getDescription());
        calendar.setStart(calendarDTO.getStart());
        calendar.setEnd(calendarDTO.getEnd());
        calendar.setBackgroundcolor(calendarDTO.getBackgroundColor());
        calendar.setTextcolor(calendarDTO.getTextColor());
        calendar.setIsallday(calendarDTO.isIsallday());

        calendarRepository.save(calendar);
    }
}
