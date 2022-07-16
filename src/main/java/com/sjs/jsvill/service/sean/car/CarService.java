package com.sjs.jsvill.service.sean.car;

import com.sjs.jsvill.dto.sean.CarDTO;

import java.util.List;

public interface CarService {

    void register(List<CarDTO> carDTOList);

    List<String> carCheck(List<String> numberList);


}
