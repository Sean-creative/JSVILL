package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contarct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contarct, Long> {

    //특정 유닛의 모든 계약을 가져온다. (지난 계약은 가져오지 않는다)
    //하나의 계약자에 대한 하나의 계약을 가져온다. -> 이때 제일 최신의 계약을 가져와야한다.
//    @Query("select c from Contarct c left join c.tenant t left outer join Unit u " +
//            "on u=t.unit where t.unit.unit_rowid=:unit_rowid " +
//            "and t.livingType._livingtype_rowid<>10 and c.enddate>=current_date")
//    List<Contarct> findContarctByUnit(@Param("unit_rowid") Long unit_rowid);

}
