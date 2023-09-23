package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.ContractTenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractTenantRepository extends JpaRepository<ContractTenant, Long> {
    List<ContractTenant> findAllByContract(Contract contract);

    //조회해야하는 호실 rowid를 한번에 던져서 현재 진행중인 계약의 리스트를 가져옴
    @Query("SELECT COUNT (ct) FROM ContractTenant ct LEFT JOIN ct.contract c WHERE ct.contract.contract_rowid IN :contractRowids")
    Long findProgressContractTenantsByContract(@Param("contractRowids") List<Long> contractRowids);
}
