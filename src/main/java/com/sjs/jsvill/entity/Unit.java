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
    private Long building_rowid;

    @Column(length = 100, nullable = false)
    private Long option_rowid;

    @Column(length = 100, nullable = false)
    private Long car_rowid;

    @Column(length = 100, nullable = false)
    private Long tenant_rowid;

    @Column(length = 64, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private Long deposit;

    @Column(length = 100, nullable = false)
    private Long monthly;

    @Column(length = 100)
    private Long managementfees;

    @Column(length = 100, nullable = false)
    private String paymentdate;

    @Column(length = 100)
    private String note;



    public void changeTitle(String title) {
        this.title = title;
    }
}