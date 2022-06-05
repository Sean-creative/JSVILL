package com.sjs.jsvill.service.sean.tenant;


import com.sjs.jsvill.entity.sean.Tenant;

import java.util.List;

public interface TenantService {
    Tenant get(String phone);
    List<Tenant> getTenantList(Long contractRowid);
}
