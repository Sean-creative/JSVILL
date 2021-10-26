package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Unit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unit_rowid;

    @Column(length = 100, nullable = false)
    private Long group_rowid;

    @Column(length = 100, nullable = false)
    private Long option_rowid;

    @Column(length = 100, nullable = false)
    private Long car_rowid;

    @Column(length = 100, nullable = false)
    private Long tenant_rowid;

    @Column(length = 100, nullable = false)
    private String addr2;



    @Column(length = 100)
    private String note;
}