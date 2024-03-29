package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    //한개의 로우내에 Object[]로 나온다
    //해당 그룹에 있는 유닛들을 가져오는 쿼리
    @Query("select u from Group g LEFT JOIN Unit u On u.group=g where u.group.group_rowid=:group_rowid")
    List<Unit> getUnitByGroup(@Param("group_rowid") Long group_rowid);

    //해당 그룹의 총합을 계산해주는 쿼리
//    @Query("select count(u), sum(u.deposit), sum(u.rentfee), sum(u.managementfees) from Group g LEFT JOIN Unit u On u.group=g where g.group_rowid=:group_rowid group by g")
//    Object getGroupWithUnitGroupBy(@Param("group_rowid") Long group_rowid);

    //해당 멤버가 가지고 있는 그룹을 가져오는 쿼리
    @Query("select g from Group g LEFT JOIN GroupMember gm On gm.group=g where gm.member.memberRowid=:memberRowid")
    List<Group> getGroupByMember(@Param("memberRowid") Long memberRowid);

}
