package com.sjs.jsvill.repository.sean;

import com.sjs.jsvill.entity.sean.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    //특정 유닛의 모든 계약을 가져온다. 지난 계약은 가져오지 않는다 -> 현재꺼랑 미래것 해서 두개여야함
    @Query("select c from Contract c left join c.unit u where c.unit.unit_rowid=:unit_rowid and c.enddate>=current_date order by c.enddate")
    List<Contract> findContarctByUnitNotOld(@Param("unit_rowid") Long unit_rowid);

}
