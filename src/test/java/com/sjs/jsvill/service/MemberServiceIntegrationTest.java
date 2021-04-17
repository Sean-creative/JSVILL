//package com.sjs.jsvill.service;
//
//import hello.hellospring.domain.Member;
//import hello.hellospring.repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest
//@Transactional
//class MemberServiceIntegrationTest {
//
//    @Autowired MemberService memberService;
//    @Autowired MemberRepository memberRepository;
//
//    @Test
//    //테스트는 한글로도 많이 적는다.
//    //테스트 코드는 실제 코드에 영향주지x
//    void 회원가입() {
//        //given 뭔가가 주어졌는데
//        Member member = new Member();
//        member.setName("spring");
//
//        //when 이거를 실행했을 때
//        Long saveId = memberService.join(member);
//
//        //then 이게 나와야해
//        Member findMember = memberService.findOne(saveId).get();
//        assertThat(member.getName()).isEqualTo(findMember.getName());
//    }
//
//    @Test
//    public void 중복_회원_예외() {
//        //given
//        Member member1 = new Member();
//        member1.setName("spring");
//
//        Member member2 = new Member();
//        member2.setName("spring");
//
//        //when
//        memberService.join(member1);
//        // ctrl + alt + v  -> 앞에 변수만들어줌
//        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//
//    }
//
//}