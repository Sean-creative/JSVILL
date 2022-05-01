package com.sjs.jsvill.dto.sean;

import com.sjs.jsvill.entity.sean.Tenant;
import com.sjs.jsvill.entity.sub._LivingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TenantDTO {
    private Long tenantRowid;
    private String title;
    private String phone;
    private Boolean isContractor;
    private Long livingType;

    public static List<Tenant> DTOToEntities(List<TenantDTO> tenantDTOList) {
        //entity List로 만들어서 반환해야함
        List<Tenant> tenantList = new ArrayList<>();

        for (TenantDTO tenantDTO : tenantDTOList) {
            Tenant tenant = Tenant.builder()
                    ._livingtype(_LivingType.builder()._livingtype_rowid(tenantDTO.livingType).build())
                    .title(tenantDTO.title)
                    .phone(tenantDTO.phone)
                    .iscontractor(tenantDTO.isContractor)
                    .build();
            tenantList.add(tenant);
        }
        return tenantList;
    }
}
