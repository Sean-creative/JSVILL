package com.sjs.jsvill.service.calendar;

import com.sjs.jsvill.dto.CalendarDTO;
import com.sjs.jsvill.entity.Calendar;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.repository.CalendarRepository;
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
    public Long register(CalendarDTO calendarDTO) {
        log.info("calendarDTO : " + calendarDTO);

        Calendar calendar = dtoToEntity(calendarDTO);
        log.info("calendar : " + calendar);

        return calendarRepository.save(calendar).getCalendar_rowid();
    }

//    @Override
//    @Transactional
//    public Long remove(Long calendar_rowid) {
//        calendarRepository.deleteById(calendar_rowid);
//        return calendar_rowid;
//    }
//
//    @Override
//    public Calendar get(Long calendar_rowid) {
//        Optional<Calendar> calendar = calendarRepository.findById(calendar_rowid);
//        return calendar.get();
//    }
//
//    @Transactional
//    @Override
//    public void modify(CalendarDTO calendarDTO) {
//        Calendar calendar = calendarRepository.getById(calendarDTO.getCalendarRowid());
//
//        if(calendar != null) {
//            calendar.changeTitle(calendarDTO.getTitle());
//            calendar.changeLandAddr(calendarDTO.getLandAddr());
//            calendar.changePostNum(calendarDTO.getPostNum());
//            calendar.changeMemo(calendarDTO.getMemo());
//            calendar.changeCompletionDate(calendarDTO.getCompletionDate());
//            calendarRepository.save(calendar);
//        }
//    }
}
