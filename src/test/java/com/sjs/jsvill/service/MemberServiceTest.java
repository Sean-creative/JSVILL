//package com.sjs.jsvill.service;
//
//import hello.hellospring.domain.Member;
//import hello.hellospring.repository.MemoryMemberRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//class MemberServiceTest {
//
//    MemberService memberService;
//    MemoryMemberRepository memberRepository;
//
//    @BeforeEach
//    public void beforeEach() {
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//    }
//
//    @AfterEach
//    //테스트와 의존관계 없애기 위해 -> 하나의 테스트가 끝날 때마다 공용데이터를 삭제하는메소드
//    public void afterEach() {
//        memberRepository.clearStore();
//    }
//
//    @Test
//    //테스트는 한글로도 많이 적는다.
//    //테스트 코드는 실제 코드에 영향주지x
//    void 회원가입() {
//        //given 뭔가가 주어졌는데
//        Member member = new Member();
//        member.setName("hello");
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
////        try {
////            memberService.join(member2);
////            fail();
////        }catch (IllegalStateException e) {
////            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
////        }
//
//        //then
//    }
//
//    @Test
//    void findOne() {
//    }
//}