package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testRegister() {
        System.out.println("memberRepository : " + memberRepository);
        IntStream.rangeClosed(1, 3).forEach(i -> {
            Member member = Member.builder()
                    .userid("userid" + i)
                    .email("email" + i)
                    .pw("pw" + i)
                    .username("username" + i)
                    .build();
            System.out.println(memberRepository.save(member));
        });
    }
}
