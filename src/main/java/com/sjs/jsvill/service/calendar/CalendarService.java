package com.sjs.jsvill.service.calendar;

import com.sjs.jsvill.dto.CalendarDTO;
import com.sjs.jsvill.entity.Calendar;
import com.sjs.jsvill.entity.Group;

import java.util.ArrayList;
import java.util.List;

public interface CalendarService {
    List<CalendarDTO> getList(Long groupRowid);
    List<CalendarDTO> findEventsWithinNextWeek();
    void register(CalendarDTO dto);
    void remove(Long calendarRowid, Boolean isAllDelete);
    void modify(CalendarDTO calendarDTO);
}
