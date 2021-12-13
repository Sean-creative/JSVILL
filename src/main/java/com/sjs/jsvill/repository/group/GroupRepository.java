package com.sjs.jsvill.repository.group;

import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    //1L에 대한 그룹 총 개수
    @Query("select g,count(g) from Group g where g.member=:member")
    List<Object> getGroupWithAll(Member member);

}
