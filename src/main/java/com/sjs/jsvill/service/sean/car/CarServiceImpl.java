package com.sjs.jsvill.service.sean.car;

import com.sjs.jsvill.dto.sean.CarDTO;
import com.sjs.jsvill.entity.sean.Car;
import com.sjs.jsvill.repository.sean.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    @Override
    public void register(List<CarDTO> carDTOList) {
        List<Car> carList =new ArrayList<>();
        carDTOList.forEach(carDTO -> {
            carList.add(Car.DTOToEntity(carDTO));
        });
        carRepository.saveAll(carList);
    }

    @Override
    public List<String> carCheck(List<String> numberList) {
        List<String> result = new ArrayList<>();

        numberList.forEach(number -> {
            if(carRepository.existsByNumber(number))
                result.add(number);
        });
        return result;
    }
}
