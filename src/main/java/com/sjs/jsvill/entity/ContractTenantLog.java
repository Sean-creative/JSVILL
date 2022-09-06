package com.sjs.jsvill.entity;

import com.sjs.jsvill.entity.common.BaseEntity;
import com.sjs.jsvill.entity.sub._LivingType;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="contract_tenant_log")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"contract", "tenant", "_livingtype"})
public class ContractTenantLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contract_tenantLog_rowid;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "contract_rowid", nullable = false)
    private Contract contract;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "tenant_rowid", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "_livingtype_rowid", nullable = false)
    private _LivingType _livingtype;

    public static ContractTenantLog ContractTenantToContractTenantLog(ContractTenant contractTenant, _LivingType _livingtype) {
        return  ContractTenantLog.builder().contract(contractTenant.getContract()).tenant(contractTenant.getTenant())._livingtype(_livingtype).build();
    }
}