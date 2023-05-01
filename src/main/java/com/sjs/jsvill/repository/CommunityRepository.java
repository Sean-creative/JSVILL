package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Notice, Long> {
    List<Notice> findAll();
}
