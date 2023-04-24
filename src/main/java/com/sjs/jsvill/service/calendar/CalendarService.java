package com.sjs.jsvill.service.calendar;

import com.sjs.jsvill.dto.CalendarDTO;
import com.sjs.jsvill.entity.Calendar;
import com.sjs.jsvill.entity.Group;

import java.util.List;

public interface CalendarService {
    List<CalendarDTO> getList(Long groupRowid);
    Long register(CalendarDTO dto);
    Long remove(Long calendarRowid);
    void modify(CalendarDTO calendarDTO);


    //파라미터로 받는건 DTO인데 -> DB에 접근하는 데이터는 엔티티로 바꿔줘야함
    default Calendar dtoToEntity(CalendarDTO calendarDTO) {
        Calendar calendar = Calendar.builder()
                .calendar_rowid(calendarDTO.getCalendarRowid())
                .group(Group.builder().group_rowid(calendarDTO.getGroupRowid()).build())
                .bundleid(calendarDTO.getBundleId())
                .title(calendarDTO.getTitle())
                .description(calendarDTO.getDescription())
                .start(calendarDTO.getStart())
                .end(calendarDTO.getEnd())
                .repetition(calendarDTO.getRepetition())
                .backgroundcolor(calendarDTO.getBackgroundColor())
                .textcolor(calendarDTO.getTextColor())
                .isallday(calendarDTO.isIsallday())
                .build();
        return calendar;
    }
}
