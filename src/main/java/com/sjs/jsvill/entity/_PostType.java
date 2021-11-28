package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="_posttype")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class _PostType {

    @Id
    private Long _posttype_rowid;

    @Column(length = 64, nullable = false)
    private String title;
}