package com.sjs.jsvill.service.tenant;

import com.sjs.jsvill.entity.Tenant;
import com.sjs.jsvill.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
    public List<Tenant> getTenantList(Long contractRowid) {
        return tenantRepository.findByContractRowid(contractRowid);
    }
}
