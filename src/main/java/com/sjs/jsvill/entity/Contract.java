package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="contract")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Contract extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contract_rowid;

    @Column(length = 100, nullable = false)
    private Long unit_rowid;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String startdate;

    @Column(length = 100, nullable = false)
    private String enddate;

    @Column(length = 100, nullable = false)
    private Boolean isprogressing;

    @Column(length = 100, nullable = false)
    private Long deposit;

    @Column(length = 100, nullable = false)
    private Long monthly;

    @Column(length = 100, nullable = false)
    private Long managementfees;

    @Column(length = 100, nullable = false)
    private String paymentdate;
}