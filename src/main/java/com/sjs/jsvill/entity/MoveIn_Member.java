package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="movein_member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"member", "moveIn"})
public class MoveIn_Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movein_member_rowid;

    @ManyToOne
    @JoinColumn(name = "member_rowid")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "movein_rowid")
    private MoveIn moveIn;

    @Column(nullable = false)
    private Boolean isactive;
}