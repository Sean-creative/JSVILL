package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contarct;
import com.sjs.jsvill.entity.Tenant;
import com.sjs.jsvill.entity._ContractType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.LongStream;

@SpringBootTest
public class ContarctRepositoryTests {

    @Autowired
    private ContractRepository contractRepository;

    @Test
    public void testRegister() {
        Tenant tenant = Tenant.builder().tenant_rowid(1L).build();
        _ContractType contractType = _ContractType.builder()._contracttype_rowid(10L).build();

        LongStream.rangeClosed(1, 3).forEach(i -> {
            Contarct contarct = Contarct.builder()
                    .tenant(tenant)
                    .contractType(contractType)
                    .startdate("startdate" + i)
                    .enddate("enddate" + i)
                    .deposit(i)
                    .rentfee(i)
                    .managementfees(i)
                    .paymentday(i)
                    .build();
            System.out.println(contractRepository.save(contarct));
        });
    }

    @Test
    public void findByUnit() {
        List<Contarct> result = contractRepository.findContarctByUnit(1L);
        System.out.println("result.length : " + result.size());

        result.forEach(i -> System.out.println(i));
    }
}
