package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_rowid;

    @Column(length = 64, nullable = false)
    private String userid;

    @Column(length = 64, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String pw;

    @Column(nullable = false)
    private String username;

}