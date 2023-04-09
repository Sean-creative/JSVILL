package com.sjs.jsvill.service.calendar;

import com.sjs.jsvill.dto.CalendarDTO;
import com.sjs.jsvill.entity.Calendar;

import java.util.List;

public interface CalendarService {
    List<CalendarDTO> getList(Long groupRowid);
    Long register(CalendarDTO dto);
//    Long remove(Long calendar_rowid);
//    Calendar get(Long calendar_rowid);
//    void modify(CalendarDTO calendarDTO);


    //파라미터로 받는건 DTO인데 -> DB에 접근하는 데이터는 엔티티로 바꿔줘야함
    default Calendar dtoToEntity(CalendarDTO calendarDTO) {
        Calendar calendar = Calendar.builder()
                .calendar_rowid(calendarDTO.getCalendarRowid())
                .group(calendarDTO.getGroup())
                .id(calendarDTO.getId())
                .title(calendarDTO.getTitle())
                .description(calendarDTO.getDescription())
                .start(calendarDTO.getStart())
                .end(calendarDTO.getEnd())
                .backgroundcolor(calendarDTO.getBackgroundColor())
                .textcolor(calendarDTO.getTextColor())
                .isallday(calendarDTO.isAllday())
                .build();
        return calendar;
    }
}
