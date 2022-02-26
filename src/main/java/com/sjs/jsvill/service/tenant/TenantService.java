package com.sjs.jsvill.service.tenant;

import com.sjs.jsvill.dto.TenantDTO;
import com.sjs.jsvill.entity.Tenant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface TenantService {


    default List<TenantDTO> entitiesToDTO(List<Tenant> tenantList) {
        List<TenantDTO> tenantDTOList = new ArrayList<>();
        if(!tenantList.isEmpty()) {
            tenantDTOList = tenantList.stream().map(tenant -> TenantDTO.builder()
                    .title(tenant.getTitle())
                    .phone(tenant.getPhone())
//                    .isContractor(tenant.get())
                    .build()
            ).collect(Collectors.toList());
        }
        return tenantDTOList;
    }
}
