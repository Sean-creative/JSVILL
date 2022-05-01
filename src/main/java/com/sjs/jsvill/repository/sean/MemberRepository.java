package com.sjs.jsvill.repository.sean;

import com.sjs.jsvill.entity.sean.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
