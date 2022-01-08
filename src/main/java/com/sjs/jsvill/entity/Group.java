package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="`group`")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "groupType")
public class Group extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long group_rowid;

    @ManyToOne
    @JoinColumn(name = "_grouptype_rowid", nullable = false)
    private _GroupType groupType;

    @Column(length = 64, nullable = false)
    private String title;

    @Column(nullable = false)
    private String addr1;

    @Column(length = 10, nullable = false)
    private String postnum;

    @Column(nullable = false)
    private String memo;
}