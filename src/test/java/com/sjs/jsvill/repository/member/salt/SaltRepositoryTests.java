package com.sjs.jsvill.repository.member.salt;

import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity._MemberType;
import com.sjs.jsvill.entity._Salt;
import com.sjs.jsvill.repository.MemberRepository;
import com.sjs.jsvill.repository._SaltRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

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
