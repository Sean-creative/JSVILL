package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Tenant;
import com.sjs.jsvill.entity._LivingType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.LongStream;

import static org.reflections.Reflections.log;

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
    }

    @Test
    public void findByUnit() {
        List<Tenant> result = tenantRepository.findByContract(1L);
        System.out.println("result.length : " + result.size());
        for (Tenant tenant : result) {
            System.out.println("tenant : " + tenant);
        }
    }

    @Test
    public void findAllByPhoneIn() {
        List<String> list = new ArrayList<>();
        list.add("010");
        list.add("111");

        List<Tenant> tenantList = tenantRepository.findAllByPhoneIn(list);
        log.info("tenantRepository.size() : " + tenantList.size());
        log.info("tenantRepository.size() : " + tenantList.isEmpty());
        for(Tenant tenant : tenantList) {
            log.info("tenant : " + tenant);
//            Json.stringToJson(tenant);
        }
    }
}
