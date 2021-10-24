package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Building extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long building_rowid;

    @Column(length = 100, nullable = false)
    private Long member_rowid;

    @Column(length = 100, nullable = false)
    private Integer _buildingtype_rowid;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String addr1;

    @Column(length = 100, nullable = false)
    private String addr2;

    @Column(length = 100, nullable = false)
    private String postnum;

    @Column(length = 100)
    private String memo;

    @Column(length = 100, nullable = false)
    private Integer tenantcnt;

    @Column(length = 100, nullable = false)
    private Long deposit;

    @Column(length = 100, nullable = false)
    private Long monthly;

    @Column(length = 100, nullable = false)
    private Long managementfees;


    public void changeTitle(String title) {
        this.title = title;
    }
}