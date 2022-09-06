package com.sjs.jsvill.entity;

import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="memberuser")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberuser_rowid;

    @OneToOne
    @JoinColumn(name = "member_rowid")
    private Member member;

    @Column(length = 64, nullable = false)
    private String phone;

    @Column(length = 64, nullable = false)
    private String pin;


}