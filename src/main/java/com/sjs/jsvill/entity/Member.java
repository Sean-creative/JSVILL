package com.sjs.jsvill.entity;

import com.sjs.jsvill.entity.common.BaseEntity;
import com.sjs.jsvill.entity.sub._MemberType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @OneToOne
    @JoinColumn(name = "_membertype_rowid")
    private _MemberType _memberType;

    @Column(nullable = false)
    private String name;

    @CreatedDate //@CreatedDate는 JPA에서 엔티티의 생성 시간을 처리한다.
    @Column(name = "regdate", updatable = false) //updatable=false를 통해서 해당 엔티티 객체를 데이터베이스에 반영할 때 regdate 칼럼값은 변경되지 않는다.
    private LocalDateTime regDate;
}