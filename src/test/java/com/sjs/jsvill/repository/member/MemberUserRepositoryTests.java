package com.sjs.jsvill.repository.member;

import com.sjs.jsvill.entity.sean.Member;
import com.sjs.jsvill.entity.jimmy.MemberUser;
import com.sjs.jsvill.repository.jimmy.MemberUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberUserRepositoryTests {

    @Autowired
    private MemberUserRepository memberUserRepository;

    @Test
    public void testRegister() {
        Member member = Member.builder().member_rowid(8L).build();
        //IntStream.rangeClosed(1, 3).forEach(i -> {
            MemberUser memberUser = MemberUser.builder()
//                    .member_rowid(member)
                    .phone("01032223333")
                    .pin("1111")
                    .build();
            System.out.println(memberUserRepository.save(memberUser));

        //});
    }
}
