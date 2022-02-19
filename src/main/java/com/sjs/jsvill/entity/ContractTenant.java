package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="contract_tenant")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"contract", "tenant"})
public class ContractTenant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contract_tenant_rowid;

    @ManyToOne
    @JoinColumn(name = "contract_rowid", nullable = false)
    private Contarct contract;

    @ManyToOne
    @JoinColumn(name = "tenant_rowid", nullable = false)
    private Tenant tenant;
}