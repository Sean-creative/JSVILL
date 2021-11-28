package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="unit")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Unit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unit_rowid;

    @ManyToOne
    @JoinColumn(name = "group_rowid")
    private Group group;

//    @ManyToOne
//    @JoinColumn(name = "option_rowid")
//    private Option option_rowid;

//    @ManyToOne
//    @JoinColumn(name = "car_rowid")
//    private Car car;

//    @ManyToOne
//    @JoinColumn(name = "tenant_rowid")
//    private Tenant tenant ;

    @Column(length = 100, nullable = false)
    private String addr2;
}