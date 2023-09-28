package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.ContractTenantLog;
import com.sjs.jsvill.entity.Tenant;
import com.sjs.jsvill.entity.defaultType._LivingType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.LongStream;

@SpringBootTest
public class ContractTenantLogRepositoryTests {

    @Autowired
    private ContractTenantLogRepository contractTenantLogRepository;

    @Test
    public void testRegister() {
        //A계약에 3개의 세입자를 추가
        Contract contarct = Contract.builder().contract_rowid(83L).build();
        LongStream.rangeClosed(67, 69).forEach(i -> {
            ContractTenantLog contractTenantLog = ContractTenantLog.builder()
                    .contract(contarct)
                    .tenant(Tenant.builder().tenant_rowid(i).build())
                    ._livingtype(_LivingType.builder()._livingtype_rowid(10L).build())
                    .build();
            System.out.println(contractTenantLogRepository.save(contractTenantLog));
        });
    }

}
