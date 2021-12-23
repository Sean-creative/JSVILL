package com.sjs.jsvill.repository.group;

import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<Group, Long> {

    //1L에 대한 그룹 총 개수,,,,,,
    @Query(value = "select * from group g where g.member_rowid=:member_rowid", nativeQuery = true)
    Page<Object[]> getGroupWithAll(PageRequest pageRequest, @Param("member_rowid") Long member_rowid);

}
