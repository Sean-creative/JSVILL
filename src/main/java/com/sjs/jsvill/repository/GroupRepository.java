package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

    //1L에 대한 그룹 총 개수
//    @Query(value = "select * from group g where g.member_rowid=:member_rowid", nativeQuery = true)
//    Page<Object[]> getGroupWithAll(PageRequest pageRequest, @Param("member_rowid") Long member_rowid);

}
