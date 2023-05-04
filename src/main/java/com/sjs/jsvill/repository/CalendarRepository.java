package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Calendar;
import com.sjs.jsvill.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    List<Calendar> findAllByGroup(Group group);
    void deleteByBundleid(Long bundleId);

    //한개의 로우내에 Object[]로 나온다
    //해당 그룹에 있는 유닛들을 가져오는 쿼리
//    @Query("select u from Group g LEFT JOIN Unit u On u.group=g where g.group_rowid=:group_rowid")
//    List<Calendar> getUnitWithGroup(@Param("group_rowid") Long group_rowid);

    //해당 멤버가 가지고 있는 그룹을 가져오는 쿼리
//    @Query("select g from Group g LEFT JOIN GroupMember gm On gm.group=g where gm.member.memberRowid=:memberRowid")
//    List<Group> getGroupWithAll(@Param("memberRowid") Long memberRowid);
}
