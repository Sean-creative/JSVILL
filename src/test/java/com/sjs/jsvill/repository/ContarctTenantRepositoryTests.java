package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contarct;
import com.sjs.jsvill.entity.ContractTenant;
import com.sjs.jsvill.entity.Tenant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.LongStream;

@SpringBootTest
public class ContarctTenantRepositoryTests {

    @Autowired
    private ContractTenantRepository contractTenantRepository;

    @Test
    public void testRegister() {
        //A계약에 3개의 세입자를 추가
        Contarct contarct = Contarct.builder().contract_rowid(1L).build();
        LongStream.rangeClosed(1, 3).forEach(i -> {
            ContractTenant contractTenant = ContractTenant.builder()
                    .contract(contarct)
                    .tenant(Tenant.builder().tenant_rowid(i).build())
                    .build();
            System.out.println(contractTenantRepository.save(contractTenant));
        });
    }

//    @Test
//    public void findByUnit() {
//        List<Contarct> result = contractRepository.findContarctByUnit(1L);
//        System.out.println("result.length : " + result.size());
//
//        result.forEach(i -> System.out.println(i));
//    }
}
