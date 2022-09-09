package com.sjs.jsvill.controller.tenant;

import com.sjs.jsvill.entity.Tenant;
import com.sjs.jsvill.service.tenant.TenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("/tenant/check")
    public boolean action(@RequestParam("phone") String phone) {
        boolean result = false;
        Tenant tenant = tenantService.get(phone);
        //현재 세입자만 로그인이 가능하다. 나중에 기획에 따라 변경 될 수도 있긴 함
        if(tenant!=null && tenant.get_livingtype().get_livingtype_rowid() == 20) result = true;
        return result;
    }
}
