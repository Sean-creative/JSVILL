package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.MemberUser;
import com.sjs.jsvill.entity.Tenant;
import com.sjs.jsvill.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
    Tenant findByPhone(String phone);

}
