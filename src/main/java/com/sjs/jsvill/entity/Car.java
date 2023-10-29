package com.sjs.jsvill.entity;

import com.sjs.jsvill.dto.CarDTO;
import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "car")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"tenant"})
public class Car extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long car_rowid;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "tenant_rowid")
    private Tenant tenant;

    @Column(length = 64, nullable = false)
    private String title;

    @Column(length = 64, nullable = false, unique = true)
    private String number;


    public static Car DTOToEntity(CarDTO carDTO) {
        Tenant tenant = Tenant.builder().tenant_rowid(carDTO.getTenantRowid()).build();
        return Car.builder()
                .tenant(tenant)
                .title(carDTO.getTitle())
                .number(carDTO.getNumber())
                .build();
    }
}