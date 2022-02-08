package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Letter;
import com.sjs.jsvill.entity.MemberAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter, Long> {


}
