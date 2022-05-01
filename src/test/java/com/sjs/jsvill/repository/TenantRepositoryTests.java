package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.sean.Tenant;
import com.sjs.jsvill.entity.sub._LivingType;
import com.sjs.jsvill.repository.sean.TenantRepository;
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
        List<Tenant> result = tenantRepository.findByContractRowid(1L);
        System.out.println("result.length : " + result.size());
        for (Tenant tenant : result) {
            System.out.println("tenant : " + tenant);
        }
    }

    @Test
    public void findAllByPhoneIn() {
        List<String> phoneList = new ArrayList<>();
        phoneList.add("010");
        phoneList.add("111");

        Tenant tenant = tenantRepository.findAllByPhoneIn(phoneList);
        log.info("tenant : " + tenant);
    }

    @Test
    public void findAllByPhone() {
        Tenant tenant = tenantRepository.findByPhone("0102222");
        log.info("tenant : " + tenant);
    }

    @Test
    public void findByPhone() {
        Boolean tenant = tenantRepository.existsByPhone("sdsdcvvb");
        log.info("tenant : " + tenant);
    }
}
