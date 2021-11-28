package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="car")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long car_rowid;

    @ManyToOne
    @JoinColumn(name = "movein_rowid")
    private MoveIn moveIn;

    @Column(length = 64, nullable = false)
    private String title;
}