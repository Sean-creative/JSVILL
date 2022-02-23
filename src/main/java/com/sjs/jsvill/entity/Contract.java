package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="contract")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"unit", "_contracttype"})
public class Contract extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contract_rowid;

    @ManyToOne
    @JoinColumn(name = "unit_rowid")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "_contracttype_rowid")
    private _ContractType _contracttype;

    @Column(length = 100, nullable = false)
    private String startdate;

    @Column(length = 100, nullable = false)
    private String enddate;

    @Column(nullable = false)
    private Long deposit;

    @Column(nullable = false)
    private Long rentfee;

    @Column(nullable = false)
    private Long managementfees;

    @Column(length = 100, nullable = false)
    private Long paymentday;
}