package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="`groupmember`")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"member", "group"})
public class GroupMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupmember_rowid;

    @ManyToOne
    @JoinColumn(name = "member_rowid", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "group_rowid", nullable = false)
    private Group group;
}