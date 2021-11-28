package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tenant")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tenant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tenant_rowid;

    @ManyToOne
    @JoinColumn(name = "unit_rowid")
    private Unit unit;

    @Column(length = 64, nullable = false)
    private String title;

    @Column(length = 64, nullable = false)
    private String phone;

    @Column(length = 1, nullable = false)
    private Integer ishead;
}