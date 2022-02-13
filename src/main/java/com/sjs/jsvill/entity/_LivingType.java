package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="_livingtype")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class _LivingType {

    @Id
    private Long _livingtype_rowid;

    @Column(length = 64, nullable = false)
    private String title;
    //    10	거주 X
    //    20	거주 O
    //    30	거주 예정
}