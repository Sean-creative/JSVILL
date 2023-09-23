package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.ContractTenant;
import com.sjs.jsvill.entity.Tenant;
import com.sjs.jsvill.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

@SpringBootTest
public class ContractTenantRepositoryTests {

    @Autowired
    private ContractTenantRepository contractTenantRepository;

    @Test
    public void testRegister() {
        //A계약에 3개의 세입자를 추가
        Contract contarct = Contract.builder().contract_rowid(1L).build();
        LongStream.rangeClosed(1, 3).forEach(i -> {
            ContractTenant contractTenant = ContractTenant.builder()
                    .contract(contarct)
                    .tenant(Tenant.builder().tenant_rowid(i).build())
                    .build();
            System.out.println(contractTenantRepository.save(contractTenant));
        });
    }

    @Test
    public void findAllByContract() {
        Contract contract = Contract.builder().contract_rowid(34L).build();
        List<ContractTenant> result = contractTenantRepository.findAllByContract(contract);
        result.forEach(i -> System.out.println(i.getTenant()));
    }

    @Test
    public void findAll() {
        List<ContractTenant> result = contractTenantRepository.findAll();
        result.forEach(i -> System.out.println(i));
    }

    @Test
    public void findProgressContractTenantsByContract() {
        System.out.println("Count : " + contractTenantRepository.findProgressContractTenantsByContract(Arrays.asList(11L, 13L)));
    }
}
