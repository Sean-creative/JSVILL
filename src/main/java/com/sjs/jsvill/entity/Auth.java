package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="auth")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Auth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auth_rowid;

    @Column(length = 64, nullable = false)
    private String phone;

    @Column(length = 64, nullable = false)
    private String number;
}