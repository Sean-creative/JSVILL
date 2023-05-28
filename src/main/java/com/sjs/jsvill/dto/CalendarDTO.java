package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Calendar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalendarDTO {
    private Long calendarRowid;
    private Long groupRowid;
    private Long bundleId;
    private String title;
    private String description;
    private String start;
    private String end;
    private String repetition;
    private String[] startLoopDays;
    private String[] endLoopDays;
    private String repetitionEnd;
    private String backgroundColor;
    private String textColor;
    private boolean isallday; //isAllDay로 하면 항상 false로만 오는 버그가 있음
    private Integer typeNo;

    public static CalendarDTO entityToDTO(Calendar calendar) {
        return CalendarDTO.builder()
                .calendarRowid(calendar.getCalendar_rowid())
                .groupRowid(calendar.getGroup().getGroup_rowid())
                .bundleId(calendar.getBundleid())
                .title(calendar.getTitle())
                .description(calendar.getDescription())
                .start(calendar.getStart())
                .end(calendar.getEnd())
                .repetition(calendar.getRepetition())
                .repetitionEnd(calendar.getRepetitionEnd())
                .backgroundColor(calendar.getBackgroundcolor())
                .textColor(calendar.getTextcolor())
                .isallday(calendar.isIsallday())
                .build();
    }

    public static List<CalendarDTO> entityToDTOList(List<Calendar> calendarList) {
        List<CalendarDTO> calendarDTOList = new ArrayList<>();
        for (Calendar calendar : calendarList) calendarDTOList.add(entityToDTO(calendar));
        return calendarDTOList;
    }
}