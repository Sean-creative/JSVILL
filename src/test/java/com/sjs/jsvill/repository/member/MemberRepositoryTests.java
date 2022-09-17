package com.sjs.jsvill.repository.member;

import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity.enm.MemberRole;
import com.sjs.jsvill.repository.MemberRepository;
import com.sjs.jsvill.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {
    // DB에 들어가는 테스트........
    // Member Entity를 만들어서 데이터를 넣는다.
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {
        //1~3 -> user
        //4~5 -> admin
        IntStream.rangeClosed(1, 5).forEach(i -> {
            Member member = Member.builder()
                    .phoneNumber("010-5007-061" + i)
                    .pinNumber(passwordEncoder.encode("111" + i))
                    .fromSocial(false)
                    .name("사용자" + i)
                    .email("111111@1111")
                    .roleSet(new HashSet<>())
                    .build();

            //default role
            member.addMemberRole(MemberRole.USER);

            if (i > 3) member.addMemberRole(MemberRole.ADMIN);

            Json.stringToJson(member, "memberTest");
            memberRepository.save(member);
        });
    }

    @Test
    public void findByPhoneNumber() {
        Optional<Member> result = memberRepository.findByPhoneNumber("010-5007-0615", false);
        Member member = result.get();
        Json.contentLog(member);
//        System.out.println("member : " + member);
    }
}
