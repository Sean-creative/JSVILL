package com.sjs.jsvill.repository.member;

import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity._Salt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberAdminRepositoryTests {

    @Autowired
    private MemberAdminRepository memberAdminRepository;

    @Test
    public void testRegister() {
        Member member = Member.builder().member_rowid(6L).build();
        _Salt salt = _Salt.builder()._salt_rowid(1L).id("c71cf").build();
        //IntStream.rangeClosed(1, 3).forEach(i -> {
            MemberAdmin memberAdmin = MemberAdmin.builder()
                    .member_rowid(member)
                    .userid("abc" + 1)
                    .pw("qwer1234")
                    .salt_id(salt)
                    .build();
            System.out.println(memberAdminRepository.save(memberAdmin));

        //});
    }
}
