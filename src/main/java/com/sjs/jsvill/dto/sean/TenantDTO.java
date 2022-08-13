package com.sjs.jsvill.dto.sean;

import com.sjs.jsvill.entity.sean.Tenant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TenantDTO {
    private Long tenantRowid;
    private Long livingType;
    private String title;
    private String phone;
    private Boolean isContractor;

    @Builder.Default
    private List<CarDTO> carDTOList = new ArrayList<>();

    public static List<TenantDTO> entitiesToDTOList(List<Tenant> tenantList, List<CarDTO> carDTOList) {
        List<TenantDTO> tenantDTOList = new ArrayList<>();
        if (!tenantList.isEmpty()) {
            tenantDTOList = tenantList.stream().map(tenant -> TenantDTO.builder()
                    .tenantRowid(tenant.getTenant_rowid())
                    .title(tenant.getTitle())
                    .phone(tenant.getPhone())
                    .isContractor(tenant.getIscontractor())
                    .livingType(tenant.get_livingtype().get_livingtype_rowid())
                    .carDTOList(carDTOList)
                    .build()
            ).collect(Collectors.toList());
        }
        return tenantDTOList;
    }

}
