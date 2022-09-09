package com.sjs.jsvill.entity;

import com.sjs.jsvill.entity.common.BaseEntity;
import com.sjs.jsvill.entity.enm.MemberRole;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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

    //내가 세입자이면서 집주인일 수 있어서, member가 애를 물면 안될듯?
//    @OneToOne
//    @JoinColumn(name = "_membertype_rowid")
//    private _MemberType _memberType;

    @Column(nullable = false, unique = true, length = 13)
    private String phone;

    @Column(nullable = false, length = 4)
    private String pinNumber;

    @Column(nullable = false)
    private boolean fromSocial;

    @Column(nullable = false, length = 32)
    private String name;

    @Column(nullable = false, length = 64)
    private String email;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<MemberRole> roleSet;

    public void addMemberRole(MemberRole clubMemberRole){
        roleSet.add(clubMemberRole);
    }
}