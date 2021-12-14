package com.sjs.jsvill.repository.test;

import com.sjs.jsvill.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
