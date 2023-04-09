package com.sjs.jsvill.entity;

import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "group_rowid")
    private Group group;

    @Column
    private String id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String start;
    @Column
    private String end;
    @Column
    private String backgroundcolor;
    @Column
    private String textcolor;
    @Column
    private boolean isallday;
}