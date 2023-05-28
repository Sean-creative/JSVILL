package com.sjs.jsvill.service.calendar;

import com.sjs.jsvill.dto.CalendarDTO;
import com.sjs.jsvill.entity.Calendar;
import com.sjs.jsvill.entity.Group;

import java.util.ArrayList;
import java.util.List;

public interface CalendarService {
    List<CalendarDTO> getList(Long groupRowid);
    void register(CalendarDTO dto);
    void remove(Long calendarRowid, Boolean isAllDelete);
    void modify(CalendarDTO calendarDTO);


    //파라미터로 받는건 DTO인데 -> DB에 접근하는 데이터는 엔티티로 바꿔줘야함
    @Deprecated
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
                .repetitionEnd(calendarDTO.getRepetitionEnd())
                .backgroundcolor(calendarDTO.getBackgroundColor())
                .textcolor(calendarDTO.getTextColor())
                .isallday(calendarDTO.isIsallday())
                .build();
        return calendar;
    }
    default List<Calendar> dtoToEntities(CalendarDTO calendarDTO) {
        List<Calendar> calendarList = new ArrayList<>();

        for (int i = 0; i < calendarDTO.getStartLoopDays().length; i++) {
            Calendar calendar = Calendar.builder()
                    .calendar_rowid(calendarDTO.getCalendarRowid())
                    .group(Group.builder().group_rowid(calendarDTO.getGroupRowid()).build())
                    .bundleid(calendarDTO.getBundleId())
                    .title(calendarDTO.getTitle())
                    .description(calendarDTO.getDescription())
                    .start(calendarDTO.getStartLoopDays()[i])
                    .end(calendarDTO.getEndLoopDays()[i])
                    .repetition(calendarDTO.getRepetition())
                    .repetitionEnd(calendarDTO.getRepetitionEnd())
                    .backgroundcolor(calendarDTO.getBackgroundColor())
                    .textcolor(calendarDTO.getTextColor())
                    .isallday(calendarDTO.isIsallday())
                    .build();
            calendarList.add(calendar);
        }

        return calendarList;
    }
}
