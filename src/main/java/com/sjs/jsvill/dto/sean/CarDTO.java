package com.sjs.jsvill.dto.sean;

import com.sjs.jsvill.entity.sean.Car;
import com.sjs.jsvill.entity.sean.Tenant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarDTO {
    private String number;
    private String title;
    private Long tenantRowid;


    public static Car DTOToEntity(CarDTO carDTO) {
        Tenant tenant = Tenant.builder().tenant_rowid(carDTO.tenantRowid).build();
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
