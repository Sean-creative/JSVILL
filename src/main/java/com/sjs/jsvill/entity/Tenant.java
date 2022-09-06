package com.sjs.jsvill.entity;

import com.sjs.jsvill.dto.TenantDTO;
import com.sjs.jsvill.entity.common.BaseEntity;
import com.sjs.jsvill.entity.sub._LivingType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tenant")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"_livingtype"})
public class Tenant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tenant_rowid;

    @ManyToOne
    @JoinColumn(name = "_livingtype_rowid", nullable = false)
    private _LivingType _livingtype;

    @Column(length = 64, nullable = false)
    private String title;

    @Column(length = 64, nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private Boolean iscontractor;

    public void changeLivingType(_LivingType _livingtype) {this._livingtype = _livingtype;}
    public void changeTilte(String title) {this.title = title;}
    public void changePhone(String phone) {this.phone = phone;}
    public void changeIsContractor(Boolean iscontractor) {this.iscontractor = iscontractor;}

    public static Tenant DTOToEntitiy(TenantDTO tenantDTO) {
        //title이나 phone이 null 인것들은 pass
        if(tenantDTO.getTitle() ==null || tenantDTO.getPhone() ==null) return null;
        return Tenant.builder()
                ._livingtype(_LivingType.builder()._livingtype_rowid(tenantDTO.getLivingType()).build())
                .title(tenantDTO.getTitle())
                .phone(tenantDTO.getPhone())
                .iscontractor(tenantDTO.getIsContractor())
                .build();
    }

    public static List<Tenant> DTOToEntities(List<TenantDTO> tenantDTOList) {
        //entity List로 만들어서 반환해야함
        List<Tenant> tenantList = new ArrayList<>();

        for (TenantDTO tenantDTO : tenantDTOList) {
            //title이나 phone이 null 인것들은 pass
            if(tenantDTO.getTitle() ==null || tenantDTO.getPhone() ==null) continue;
            Tenant tenant = Tenant.builder()
                    ._livingtype(_LivingType.builder()._livingtype_rowid(tenantDTO.getLivingType()).build())
                    .title(tenantDTO.getTitle())
                    .phone(tenantDTO.getPhone())
                    .iscontractor(tenantDTO.getIsContractor())
                    .build();
            tenantList.add(tenant);
        }
        return tenantList;
    }


}