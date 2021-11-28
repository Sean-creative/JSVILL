package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="_postdetailtype")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class _PostDetailType {

    @Id
    private Long _postdetailtype_rowid;

    @Column(length = 64, nullable = false)
    private String title;
}