package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Tenant;
import com.sjs.jsvill.entity._LivingType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import java.util.stream.LongStream;

@SpringBootTest
public class TenantRepositoryTests {

    @Autowired
    private TenantRepository tenantRepository;

    @Test
    public void register() {
        //1. 세입자가 한명인 호실일 때

        LongStream.rangeClosed(1, 3).forEach(i -> {
            Tenant tenant = Tenant.builder()
                    .title("title")
                    .phone(UUID.randomUUID().toString())
                    ._livingtype(_LivingType.builder()._livingtype_rowid(10L).build())
                    .build();
            System.out.println(tenantRepository.save(tenant));
        });

//    @Test
//    public void findByUnit() {
//        List<Contarct> result = contractRepository.findByUnit(1L);
//        System.out.println("result.length : " + result.size());
//        for (Contarct contarct : result) {
//            System.out.println("contract : " + contarct);
//        }
//    }
    }
}
