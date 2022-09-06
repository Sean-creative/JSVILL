package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    @Modifying
    @Query("delete from Unit where group.group_rowid = :group_rowid")
    Integer deleteByGroupRowid(@Param("group_rowid") Long group_rowid);

}
