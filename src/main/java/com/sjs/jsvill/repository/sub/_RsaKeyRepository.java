package com.sjs.jsvill.repository.sub;

import com.sjs.jsvill.entity.sub._RsaKey;
import com.sjs.jsvill.entity.sub._Salt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface _RsaKeyRepository extends JpaRepository<_RsaKey, Long> {

    @Query("select s from _Salt s where s.id=:id")
    _Salt getSaltById(@Param("id") String id);
}
