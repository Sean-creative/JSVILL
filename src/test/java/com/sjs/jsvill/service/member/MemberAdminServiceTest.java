package com.sjs.jsvill.service.member;


import com.sjs.jsvill.dto.MemberDTO;
import com.sjs.jsvill.dto.member.MemberAdminDTO;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity._MemberType;
import com.sjs.jsvill.entity._Salt;
import com.sjs.jsvill.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberAdminServiceTest {
    //클라이언트에서 받은 데이터를 가지고 서비스메소드 호출

    @Autowired
    MemberAdminService memberAdminService;

    @Test
    public void testRegister() {
        Member member = Member.builder().member_rowid(7L).build();
        _Salt salt = _Salt.builder()._salt_rowid(1L).id("c71cf").build();
        IntStream.rangeClosed(1, 3).forEach(i -> {
            MemberAdminDTO memberAdminDTO = MemberAdminDTO.builder()
                    .memberR(member)
                    .userId("abc" + 2)
                    .pw("qwer1234")
                    .saltId(salt)
                    .build();
            System.out.println(memberAdminService.register(memberAdminDTO));
        });
    }
}



