package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity._Salt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface _SaltRepository extends JpaRepository<_Salt, Long> {

    @Query("select s from _Salt s where s.id=:id")
    _Salt getSaltById(@Param("id") String id);
}
