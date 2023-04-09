package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Calendar;
import com.sjs.jsvill.entity.Group;
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
    private Group group;
    private String id;
    private String title;
    private String description;
    private String start;
    private String end;
    private String backgroundColor;
    private String textColor;
    private boolean isAllday;

    public static CalendarDTO entityToDTO(Calendar calendar) {
        CalendarDTO calendarDTO = CalendarDTO.builder()
                .calendarRowid(calendar.getCalendar_rowid())
                .group(calendar.getGroup())
                .id(calendar.getId())
                .title(calendar.getTitle())
                .description(calendar.getDescription())
                .start(calendar.getStart())
                .end(calendar.getEnd())
                .backgroundColor(calendar.getBackgroundcolor())
                .textColor(calendar.getTextcolor())
                .isAllday(calendar.isIsallday())
                .build();
        return calendarDTO;
    }

    public static List<CalendarDTO> entityToDTOList(List<Calendar> calendarList) {
        List<CalendarDTO> calendarDTOList = new ArrayList<>();
        for (Calendar calendar : calendarList) calendarDTOList.add(entityToDTO(calendar));
        return calendarDTOList;
    }
}