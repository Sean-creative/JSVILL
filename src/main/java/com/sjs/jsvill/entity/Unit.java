package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="unit")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "group")
public class Unit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unit_rowid;

    @ManyToOne
    @JoinColumn(name = "group_rowid", nullable = false)
    private Group group;

    @Column(length = 100, nullable = false)
    private String addr2;

    @Column(nullable = false)
    private Long deposit;

    @Column(nullable = false)
    private Long rentfee;

    @Column(nullable = false)
    private Long managementfees;

    @Column(length = 100, nullable = false)
    private Long paymentday;

    @Column(nullable = false)
    private String memo;
}