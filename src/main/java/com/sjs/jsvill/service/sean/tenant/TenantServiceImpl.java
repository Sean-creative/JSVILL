package com.sjs.jsvill.service.sean.tenant;

import com.sjs.jsvill.entity.sean.Tenant;
import com.sjs.jsvill.repository.sean.TenantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService{

    private final TenantRepository tenantRepository;

    @Override
    public Tenant get(String phone) {
        Tenant tenant = tenantRepository.findByPhone(phone);
        return tenant;
    }
}
