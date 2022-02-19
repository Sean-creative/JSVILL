package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="option")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"contarct"})
public class Option extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long option_rowid;

    @ManyToOne
    @JoinColumn(name = "contract_rowid")
    private Contarct contarct;

    //이름은 List이지만 ,로 구분하는 string 값으로 들어가 있음
    @Column(length = 64, nullable = false)
    private String optionList;
}