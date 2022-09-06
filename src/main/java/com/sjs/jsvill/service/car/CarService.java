package com.sjs.jsvill.service.car;

import com.sjs.jsvill.dto.CarDTO;

import java.util.List;

public interface CarService {

    void register(List<CarDTO> carDTOList);

    List<String> carCheck(List<String> numberList);


}
