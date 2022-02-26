package com.sjs.jsvill.service.member;


import com.sjs.jsvill.dto.member.MemberAdminDTO;
import com.sjs.jsvill.dto.member.MemberUserDTO;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity._Salt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberUserServiceTest {
    //클라이언트에서 받은 데이터를 가지고 서비스메소드 호출

    @Autowired
    MemberUserService memberUserService;

    @Test
    public void testRegister() {
        Member member = Member.builder().member_rowid(8L).build();
        //IntStream.rangeClosed(1, 3).forEach(i -> {
            MemberUserDTO memberUserDTO = MemberUserDTO.builder()
                    .memberR(member)
                    .phone("01044445555")
                    .pin("0099")
                    .build();
            System.out.println(memberUserService.register(memberUserDTO));
        //});
    }
}



