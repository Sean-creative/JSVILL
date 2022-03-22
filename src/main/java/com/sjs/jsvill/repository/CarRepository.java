package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("select c2 from ContractTenant c1 inner join Car c2 on c1.tenant=c2.tenant where c1.contract.contract_rowid=:contract_rowid")
    List<Car> findByCar(@Param("contract_rowid") Long contract_rowid);

    Boolean existsByNumber(String number);

}
