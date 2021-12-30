package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="post")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_rowid;

    @ManyToOne
    @JoinColumn(name = "member_rowid")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "_posttype_rowid")
    private _PostType postType;

    @ManyToOne
    @JoinColumn(name = "_postdetailtype_rowid")
    private _PostDetailType postDetailType;

    @Column(length = 64, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String contents;

    @Column(nullable = false)
    private Boolean isactive;
}