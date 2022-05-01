package com.sjs.jsvill.repository.member.salt;

import com.sjs.jsvill.repository.sub._SaltRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.reflections.Reflections.log;

@SpringBootTest
public class SaltRepositoryTests {

    @Autowired
    private _SaltRepository saltRepository;

    @Test
    public void getSaltById() {
        log.info(saltRepository.getSaltById("c71cf").toString());
    }
}
