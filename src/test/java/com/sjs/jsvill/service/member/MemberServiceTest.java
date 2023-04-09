package com.sjs.jsvill.service.member;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {
    //클라이언트에서 받은 데이터를 가지고 서비스메소드 호출

    @Autowired
    MemberService memberService;

    @Test
    public void testRegister() {
//        SignUpPinNewDTOReq memberDTOReq = SignUpPinNewDTOReq.builder()
//                .phoneNumber("1199")
//                .pinNumber("1199")
//                .fromSocial(false)
//                .name("qq")
//                .email("ww@22.com")
//                .roleSet(Collections.singleton(MemberRole.USER))
//                .build();
//        memberService.register(memberDTOReq);
    }
}



