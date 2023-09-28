package com.sjs.jsvill.entity.defaultType;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="_contracttype")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class _ContractType {

    @Id
    private Long _contracttype_rowid;

    @Column(length = 64, nullable = false)
    private String title;
}