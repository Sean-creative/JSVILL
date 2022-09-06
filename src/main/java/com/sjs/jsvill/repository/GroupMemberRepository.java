package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

    @Modifying
    @Query("delete from GroupMember gm where gm.group.group_rowid = :group_rowid")
    Integer deleteByGroup(@Param("group_rowid") Long group_rowid);
}
