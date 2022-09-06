package com.sjs.jsvill.service;

import com.sjs.jsvill.service.car.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class CarServiceTests {

    @Autowired
    private CarService carService;

    @Test
    public void testRegister() {

        System.out.println(carService.carCheck(Arrays.asList("1111", "2222", "0000")));
    }


}
