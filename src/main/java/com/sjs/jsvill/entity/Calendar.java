package com.sjs.jsvill.entity;

import com.sjs.jsvill.dto.CalendarDTO;
import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "calendar")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Calendar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calendar_rowid;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "group_rowid")
    private Group group;

    @Column
    private Long bundleid;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String start;
    @Column
    private String end;
    @Column
    private String repetition;
    @Column
    private String repetitionEnd;
    @Column
    private String backgroundcolor;
    @Column
    private String textcolor;
    @Column
    private boolean isallday;


    public static Calendar dtoToEntity(CalendarDTO calendarDTO, Boolean hasRowid) {
         Calendar calendar = Calendar.builder()
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
        if(hasRowid) calendar.setCalendar_rowid(calendarDTO.getCalendarRowid());
        return calendar;
    }

    public static List<Calendar> dtoToEntities(CalendarDTO calendarDTO, Boolean hasRowid) {
        List<Calendar> calendarList = new ArrayList<>();
        for (int i = 0; i < calendarDTO.getStartLoopDays().length; i++) {
            Calendar calendar = Calendar.builder()
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
            if(hasRowid) calendar.setCalendar_rowid(calendarDTO.getCalendarRowid());
            calendarList.add(calendar);
        }
        return calendarList;
    }
}