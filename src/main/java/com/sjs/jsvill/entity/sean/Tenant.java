package com.sjs.jsvill.entity.sean;

import com.sjs.jsvill.dto.sean.TenantDTO;
import com.sjs.jsvill.entity.common.BaseEntity;
import com.sjs.jsvill.entity.sub._LivingType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


    public static List<TenantDTO> entitiesToDTO(List<Tenant> tenantList) {
        List<TenantDTO> tenantDTOList = new ArrayList<>();
        if (!tenantList.isEmpty()) {
            tenantDTOList = tenantList.stream().map(tenant -> TenantDTO.builder()
                    .tenantRowid(tenant.tenant_rowid)
                    .title(tenant.getTitle())
                    .phone(tenant.getPhone())
                    .isContractor(tenant.getIscontractor())
                    .livingType(tenant.get_livingtype().get_livingtype_rowid())
                    .build()
            ).collect(Collectors.toList());
        }
        return tenantDTOList;
    }
}