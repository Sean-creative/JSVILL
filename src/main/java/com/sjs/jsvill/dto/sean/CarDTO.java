package com.sjs.jsvill.dto.sean;

import com.sjs.jsvill.entity.sean.Car;
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
    private String phone;

    public static CarDTO entityToDTO(Car car, String phone) {
        return CarDTO.builder()
                .title(car.getTitle())
                .number(car.getNumber())
                .tenantRowid(car.getTenant().getTenant_rowid())
                .phone(phone)
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
                    .build()
            ).collect(Collectors.toList());
        }
        return carDTOList;
    }
}
