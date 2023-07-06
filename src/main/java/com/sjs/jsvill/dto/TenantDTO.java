package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Tenant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TenantDTO {
    private long tenantRowid;
    private long livingType;
    private String title;
    private String phone;
    private String birthYear;
    private int age;
    private Boolean isContractor;


    public static List<TenantDTO> entitiesToDTOList(List<Tenant> tenantList) {
        List<TenantDTO> tenantDTOList = new ArrayList<>();
        if (!tenantList.isEmpty()) {
            tenantDTOList = tenantList.stream().map(tenant -> TenantDTO.builder()
                    .tenantRowid(tenant.getTenant_rowid())
                    .title(tenant.getTitle())
                    .phone(tenant.getPhone())
                    .birthYear(tenant.getBirthYear())
                    .age(calAge(tenant.getBirthYear()))
                    .isContractor(tenant.getIsContractor())
                    .livingType(tenant.get_livingtype().get_livingtype_rowid())
                    .build()
            ).collect(Collectors.toList());
        }
        return tenantDTOList;
    }

    private static int calAge(String birthYear){
        // 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.now();
        // 현재 연도 가져오기
        int currentYear = currentDate.getYear();
        // 나이 계산
        return currentYear - Integer.parseInt(birthYear);
    }
}
