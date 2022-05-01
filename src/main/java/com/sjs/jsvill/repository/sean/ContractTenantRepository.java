package com.sjs.jsvill.repository.sean;

import com.sjs.jsvill.entity.sean.Contract;
import com.sjs.jsvill.entity.sean.ContractTenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractTenantRepository extends JpaRepository<ContractTenant, Long> {
    List<ContractTenant> findAllByContract(Contract contract);
}
