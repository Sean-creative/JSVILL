package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Memo;
import com.sjs.jsvill.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findTop30ByUnitOrderByMemoRowidDesc(Unit unit);
}