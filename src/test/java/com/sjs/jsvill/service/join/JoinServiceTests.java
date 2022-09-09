package com.sjs.jsvill.service.join;

import com.sjs.jsvill.entity.Tenant;
import com.sjs.jsvill.service.tenant.TenantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JoinServiceTests {

    @Autowired
    private TenantService tenantService;

    @Test
    public void checkPhoneNumber() {
        String phone = "010-1111-1111";
        boolean result = false;
        Tenant tenant = tenantService.get(phone);
        if(tenant!=null && tenant.get_livingtype().get_livingtype_rowid() == 20) result = true;
        System.out.println("result : " + result);
    }


}
