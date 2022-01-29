package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity.MemberAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAdminRepository extends JpaRepository<MemberAdmin, Long> {


}
