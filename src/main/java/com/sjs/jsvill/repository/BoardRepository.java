package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Board;
import com.sjs.jsvill.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {


}
