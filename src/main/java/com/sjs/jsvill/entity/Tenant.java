package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tenant")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"unit"})
public class Tenant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tenant_rowid;

    @ManyToOne
    @JoinColumn(name = "unit_rowid", nullable = false)
    private Unit unit;

    @Column(length = 64, nullable = false)
    private String title;

    @Column(length = 64, nullable = false, unique = true)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "_livingtype_rowid", nullable = false)
    private _LivingType livingType;

    @Column(name = "tenantmaster_rowid", nullable = false)
    private String tenantmaster_rowid;
}