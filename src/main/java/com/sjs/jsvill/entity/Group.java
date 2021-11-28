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

    @ManyToOne
    @JoinColumn(name = "member_rowid")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "_grouptype_rowid")
    private _GroupType groupType ;

    @Column(length = 64, nullable = false)
    private String title;

    @Column(nullable = false)
    private String addr1;

    @Column(length = 10, nullable = false)
    private String postnum;

    @Column(nullable = false)
    private String memo;
}