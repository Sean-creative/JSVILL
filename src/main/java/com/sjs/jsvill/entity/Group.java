package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="`group`")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Group extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long group_rowid;

    @Column(length = 100, nullable = false)
    private Long member_rowid;

    @Column(length = 100, nullable = false)
    private Integer _grouptype_rowid;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String addr1;

    @Column(length = 100, nullable = false)
    private String postnum;

    @Column(length = 100, nullable = false)
    private String memo;

}