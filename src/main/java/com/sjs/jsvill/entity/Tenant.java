package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="`tenant`")
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
    @JoinColumn(name = "contract_rowid")
    private Contract contract;

    @Column(length = 64, nullable = false)
    private String title;

    @Column(length = 64, nullable = false)
    private String phone;

    @Column(nullable = false)
    private Integer ishead;
}