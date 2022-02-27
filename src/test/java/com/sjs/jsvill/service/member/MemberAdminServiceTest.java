package com.sjs.jsvill.service.member;


import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberAdminServiceTest {
    //클라이언트에서 받은 데이터를 가지고 서비스메소드 호출
//    주석처리함 - sean
//    @Autowired
//    MemberAdminService memberAdminService;
//
//    @Test
//    public void testRegister() {
//        Member member = Member.builder().member_rowid(7L).build();
//        _Salt salt = _Salt.builder()._salt_rowid(1L).id("c71cf").build();
//        IntStream.rangeClosed(1, 3).forEach(i -> {
//            MemberAdminDTO memberAdminDTO = MemberAdminDTO.builder()
//                    .memberR(member.getMember_rowid())
//                    .userId("abc" + 2)
//                    .pw("qwer1234")
//                    .saltId(salt.getId())
//                    .build();
//            System.out.println(memberAdminService.register(memberAdminDTO));
//        });
//    }
}



