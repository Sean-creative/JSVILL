package com.sjs.jsvill.member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);

}
