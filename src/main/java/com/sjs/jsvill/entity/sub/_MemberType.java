package com.sjs.jsvill.entity.sub;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="_membertype")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class _MemberType {

    @Id
    private Long _membertype_rowid;

    @Column(length = 64, nullable = false)
    private String title;

}