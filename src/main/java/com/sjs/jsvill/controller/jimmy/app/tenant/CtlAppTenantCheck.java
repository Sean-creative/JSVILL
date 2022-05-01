package com.sjs.jsvill.controller.jimmy.app.tenant;

import com.sjs.jsvill.entity.sean.Tenant;
import com.sjs.jsvill.service.sean.tenant.TenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
* 2022.04.09
* 현재 거주하고 있는 tenant 인지 확인
* */


@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlAppTenantCheck {

    private final TenantService tenantService;

    @ResponseBody
    @RequestMapping("/app/tenant/check/{phone}")
    public boolean action(@PathVariable("phone") String phone) {
        boolean result = false;
        Tenant tenant = tenantService.get(phone);
        if(tenant!=null && tenant.get_livingtype().get_livingtype_rowid() == 20) result = true;
        return result;
    }
}
