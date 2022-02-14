package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("select c from Car c where c.tenant.tenant_rowid=:tenant_rowid")
    List<Car> findByTenant(@Param("tenant_rowid") Long tenant_rowid);
}
