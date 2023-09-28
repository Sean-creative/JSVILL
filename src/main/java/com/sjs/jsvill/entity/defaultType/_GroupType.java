package com.sjs.jsvill.entity.defaultType;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="_grouptype")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class _GroupType {

    @Id
    private Long _grouptype_rowid;

    @Column(length = 64, nullable = false)
    private String title;
}