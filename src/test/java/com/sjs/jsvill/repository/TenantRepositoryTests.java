package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Tenant;
import com.sjs.jsvill.entity.Unit;
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
        Unit unit = Unit.builder().unit_rowid(1L).build();

        LongStream.rangeClosed(1, 3).forEach(i -> {
            Tenant tenant = Tenant.builder()
                    .unit(unit)
                    .title("title" + i)
                    .phone(UUID.randomUUID().toString())
                    .livingType(_LivingType.builder()._livingtype_rowid(i*10).build())
                    .build();
            System.out.println(tenantRepository.save(tenant));
        });
    }

//    @Test
//    public void findByUnit() {
//        List<Contarct> result = contractRepository.findByUnit(1L);
//        System.out.println("result.length : " + result.size());
//        for (Contarct contarct : result) {
//            System.out.println("contract : " + contarct);
//        }
//    }
}
