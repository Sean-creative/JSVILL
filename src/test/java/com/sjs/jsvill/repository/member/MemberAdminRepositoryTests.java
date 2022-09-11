package com.sjs.jsvill.repository.member;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberAdminRepositoryTests {
// 주석처리함 - sean
//    @Autowired
//    private MemberAdminRepository memberAdminRepository;
//
//    @Test
//    public void testRegister() {
//        Member member = Member.builder().memberRowid(6L).build();
//        _Salt salt = _Salt.builder()._salt_rowid(1L).id("c71cf").build();
//        //IntStream.rangeClosed(1, 3).forEach(i -> {
//            MemberAdmin memberAdmin = MemberAdmin.builder()
//                    .memberRowid(member)
//                    .userid("abc" + 1)
//                    .pw("qwer1234")
//                    .salt_id(salt)
//                    .build();
//            System.out.println(memberAdminRepository.save(memberAdmin));
//
//        //});
//    }
}
