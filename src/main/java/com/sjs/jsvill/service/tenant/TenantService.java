package com.sjs.jsvill.service.tenant;


import com.sjs.jsvill.entity.Tenant;

import java.util.List;

public interface TenantService {
    Tenant get(String phone);
    List<Tenant> getTenantList(Long contractRowid);
}
