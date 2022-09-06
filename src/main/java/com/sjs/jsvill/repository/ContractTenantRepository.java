package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.ContractTenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractTenantRepository extends JpaRepository<ContractTenant, Long> {
    List<ContractTenant> findAllByContract(Contract contract);
}
