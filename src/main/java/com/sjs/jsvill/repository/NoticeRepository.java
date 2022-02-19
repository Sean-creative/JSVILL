package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Board;
import com.sjs.jsvill.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {


}
