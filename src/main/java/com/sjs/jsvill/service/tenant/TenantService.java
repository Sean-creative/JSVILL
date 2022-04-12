package com.sjs.jsvill.service.tenant;


import com.sjs.jsvill.entity.Tenant;

public interface TenantService {
    Tenant get(String phone);
}
