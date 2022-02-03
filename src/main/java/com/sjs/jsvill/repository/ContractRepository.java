package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query("select c from Contract c where c.unit.unit_rowid=:unit_rowid")
    List<Contract> findByUnit(@Param("unit_rowid") Long unit_rowid);
}
