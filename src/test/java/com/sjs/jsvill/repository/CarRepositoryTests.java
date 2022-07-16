package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.sean.Car;
import com.sjs.jsvill.entity.sean.Tenant;
import com.sjs.jsvill.repository.sean.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

@SpringBootTest
public class CarRepositoryTests {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void register() {
        Tenant tenant = Tenant.builder().tenant_rowid(1L).build();
        LongStream.rangeClosed(1, 3).forEach(i -> {
            Car car = Car.builder()
                    .tenant(tenant)
                    .title("title" + i)
                    .number("number" + i)
                    .build();
            System.out.println(carRepository.save(car));
        });
    }

    @Test
    public void saveAll() {
        List<Car> carList = Arrays.asList(
                Car.builder().tenant(Tenant.builder().tenant_rowid(19L).build()).number("3333").title("3333").build(),
                Car.builder().tenant(Tenant.builder().tenant_rowid(20L).build()).number("4444").title("4444").build()
                );
        carRepository.saveAll(carList);

    }

    @Test
    public void findByCar() {
//        List<Car> result = carRepository.findByCar(1L);
//        System.out.println("result.length : " + result.size());

//        result.forEach(i -> System.out.println(i));
    }

    @Test
    public void existsByNumber() {
        System.out.println("carRepository.existsByNumber : " + carRepository.existsByNumber("342"));
    }

}
