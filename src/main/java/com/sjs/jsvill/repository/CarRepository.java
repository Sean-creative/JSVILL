package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Car;
import com.sjs.jsvill.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    Boolean existsByNumber(String number);

    List<Car> findAllByTenant(Tenant tenant);
}
