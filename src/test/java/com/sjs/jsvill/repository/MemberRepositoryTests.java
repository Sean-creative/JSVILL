package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.GroupMember;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity._GroupType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers() {
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
