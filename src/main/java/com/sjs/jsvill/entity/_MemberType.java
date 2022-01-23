package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="_membertype")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class _MemberType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _membertype_rowid;

    @Column(length = 64, nullable = false)
    private String title;

}