package com.sjs.jsvill.security;


import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity.enm.MemberRole;
import com.sjs.jsvill.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberTests {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {

        //1 - 80까지는 USER만 지정
        //81- 90까지는 USER,MANAGER
        //91- 100까지는 USER,MANAGER,ADMIN

        IntStream.rangeClosed(100,150).forEach(i -> {
            Member clubMember = Member.builder()
                    .phone(010+"-"+5007+"-"+0+i)
                    .name("사용자"+i)
                    .fromSocial(false)
                    .roleSet(new HashSet<MemberRole>())
                    .pinNumber(  passwordEncoder.encode("1111") )
                    .build();

            //default role
            clubMember.addMemberRole(MemberRole.USER);

            if(i > 125) clubMember.addMemberRole(MemberRole.ADMIN);

            repository.save(clubMember);
        });
    }

//    @Test
//    public void testRead() {
//
//        Optional<Member> result = repository.findByEmail("user95@zerock.org", false);
//
//        Member clubMember = result.get();
//
//        System.out.println(clubMember);
//
//    }
}
