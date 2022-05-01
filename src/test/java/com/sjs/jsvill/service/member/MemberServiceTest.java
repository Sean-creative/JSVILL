package com.sjs.jsvill.service.member;


import com.sjs.jsvill.dto.jimmy.member.MemberDTO;
import com.sjs.jsvill.service.jimmy.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberServiceTest {
    //클라이언트에서 받은 데이터를 가지고 서비스메소드 호출

    @Autowired
    MemberService memberService;

    @Test
    public void testRegister() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            MemberDTO memberDTO = MemberDTO.builder()
                    ._memberType_rowid(10L)
                    .name("username---------" +i)
                    .build();
            System.out.println(memberService.register(memberDTO));
        });
    }
}



