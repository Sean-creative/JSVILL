package com.sjs.jsvill.entity;

import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="reminder")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reminder extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reminder_rowid;

    @ManyToOne
    @JoinColumn(name = "memberRowid")
    private Member member; //어떤 회원에 대한 알림인지

    @Column
    private String contents; //일정내용

    @Column
    private Integer daysAgo;  //며칠전인지
}