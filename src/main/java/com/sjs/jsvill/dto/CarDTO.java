package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Car;
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
public class CarDTO {
    private Long carRowid;
    private Long tenantRowid;
    private String title;
    private String number;
    private String tenantName;

    public static CarDTO entityToDTO(Car car, String tenantName) {
        return CarDTO.builder()
                .title(car.getTitle())
                .number(car.getNumber())
                .tenantRowid(car.getTenant().getTenant_rowid())
                .tenantName(tenantName)
                .build();
    }

    public static List<CarDTO> entitiesToDTOList(List<Car> carList) {
        List<CarDTO> carDTOList = new ArrayList<>();
        if (!carList.isEmpty()) {
            carDTOList = carList.stream().map(car ->
                    CarDTO.builder()
                    .carRowid(car.getCar_rowid())
                    .tenantRowid(car.getTenant().getTenant_rowid())
                    .title(car.getTitle())
                    .number(car.getNumber())
                    .tenantName(car.getTenant().getTitle())
                    .build()
            ).collect(Collectors.toList());
        }
        return carDTOList;
    }
}
