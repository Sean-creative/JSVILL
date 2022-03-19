package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Car;
import com.sjs.jsvill.entity.Tenant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarDTO {
    private String title;
    private String number;
    private String phone;

    public static Car DTOToEntity(CarDTO carDTO, Long tenantRowid) {
        Tenant tenant = Tenant.builder().tenant_rowid(tenantRowid).build();
        if(carDTO==null) return null;
        else {
            Car car = Car.builder()
                    .tenant(tenant)
                    .title(carDTO.getTitle())
                    .number(carDTO.getNumber())
                    .build();
            return  car;
        }
    }
}
