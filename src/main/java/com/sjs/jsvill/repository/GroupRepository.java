package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    //1L에 대한 그룹 총 개수
//    @Query(value = "select * from group g where g.member_rowid=:member_rowid", nativeQuery = true)
//    Page<Object[]> getGroupWithAll(PageRequest pageRequest, @Param("member_rowid") Long member_rowid);

    //한개의 로우내에 Object[]로 나온다
    @Query("select u from Group g LEFT JOIN Unit u On u.group=g where g.group_rowid=:group_rowid")
    List<Unit> getGroupWithUnit(@Param("group_rowid") Long group_rowid);

    @Query("select count(u), sum(u.deposit), sum(u.rentfee), sum(u.managementfees) from Group g LEFT JOIN Unit u On u.group=g where g.group_rowid=:group_rowid group by g")
    Object getGroupWithUnitGroupBy(@Param("group_rowid") Long group_rowid);

    @Query("select g from Group g LEFT JOIN GroupMember gm On gm.group=g where gm.member.member_rowid=:member_rowid")
    List<Group> getGroupWithMember(@Param("member_rowid") Long member_rowid);

    //테스트 해보자
    @Query("select g, u from Group g left outer join GroupMember gm on gm.group=g " +
            "left outer join Unit u on u.group=g where gm.member.member_rowid=:member_rowid group by g")
    Object[] getGroupList(@Param("member_rowid") Long member_rowid);
}
