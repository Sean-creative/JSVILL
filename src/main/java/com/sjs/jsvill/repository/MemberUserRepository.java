package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.MemberAdmin;
import com.sjs.jsvill.entity.MemberUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberUserRepository extends JpaRepository<MemberUser, Long> {
    MemberUser findByPhone(String phone);
}
