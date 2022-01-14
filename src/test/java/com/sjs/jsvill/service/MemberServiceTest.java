package com.sjs.jsvill.service;


import com.sjs.jsvill.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void testRegister() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            MemberDTO memberDTO = MemberDTO.builder()
                    .member_rowid(Integer.toUnsignedLong(i))
                    .userid("userid" +i)
                    .email("email" +i)
                    .pw("pw" +i)
                    .username("username" +i)
                    .build();
            System.out.println(memberService.register(memberDTO));
        });
    }
}



