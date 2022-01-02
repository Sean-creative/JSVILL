package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="`option`")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"contract"})
public class Option extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long option_rowid;

    @ManyToOne
    @JoinColumn(name = "contract_rowid")
    private Contract contract;

    @Column(length = 64, nullable = false)
    private String title;
}