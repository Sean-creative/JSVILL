package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Tenant;
import com.sjs.jsvill.entity.defaultType._LivingType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
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
        System.out.println("tenant : " + tenant);
    }

    @Test
    public void findAllByPhone() {
        Tenant tenant = tenantRepository.findByPhone("010-1111-1111");
        System.out.println("tenant : " + tenant);
    }

    @Test
    public void findByPhone() {
        Boolean tenant = tenantRepository.existsByPhone("sdsdcvvb");
        System.out.println("tenant : " + tenant);
    }

    @Test
    public void findByContractRowid() {
        List<Tenant> list = tenantRepository.findByContractRowid(33L);
        for (Tenant tenant : list) {
//            Json.stringToJson(tenant);
            System.out.println("tenant : " + tenant);
        }
    }
}
