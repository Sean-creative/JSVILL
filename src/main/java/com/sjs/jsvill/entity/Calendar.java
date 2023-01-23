package com.sjs.jsvill.entity;

import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "calendar")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Calendar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calendar_rowid;
    //groupid가 필요한게 맞아?
    @Column
    private String groupid;
    @Column
    private String title;
    @Column
    private String writer;
    @Column
    private String content;
    @Column
    private String start;
    @Column
    private String end;
    @Column
    private boolean isallday;
    @Column
    private String textcolor;
    @Column
    private String backgroundcolor;
    @Column
    private String bordercolor;
}