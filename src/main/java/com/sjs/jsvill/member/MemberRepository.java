package com.sjs.jsvill.member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}
