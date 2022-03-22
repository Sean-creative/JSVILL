package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

    @Query("select t from ContractTenant ct inner join ct.tenant t where ct.contract.contract_rowid=:contract_rowid")
    List<Tenant> findByContractRowid(@Param("contract_rowid") Long contract_rowid);

    //나중에는 TRUE FALSE로 바꾸기
    Tenant findAllByPhoneIn(List<String> phones);

    Tenant findByPhone(String phone);

    Boolean existsByPhone(String phone);


}
