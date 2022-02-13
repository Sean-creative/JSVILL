package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

}
