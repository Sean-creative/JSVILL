package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    //특정 유닛의 모든 계약을 가져온다. 지난 계약은 가져오지 않는다 -> 현재랑 미래것 두개만 가져오길 바라는 쿼리임
    //프론트상으로 2개만 등록되도록 버튼을 막아놓긴 했지만, 누가 DB로 insert한다면 엉망이 될 수도...
    //TODO 딱 2개만 가져오게끔 쿼리 못짜나..?
    @Query(nativeQuery = true, value = "SELECT * FROM \n" +
            "contract c left JOIN unit u ON c.unit_rowid = u.unit_rowid\n" +
            "where c.unit_rowid=:unitRowid and c.enddate>=CURDATE() \n" +
            "order by c.enddate \n" +
            "LIMIT 2")
    List<Contract> findContarctByUnitNotOld(@Param("unitRowid") Long unitRowid);

    @Query("select c from Contract c left join c.unit u where c.unit.unit_rowid=:unitRowid and c.enddate<current_date order by c.enddate asc")
    List<Contract> findContractByUnitOldAsc(@Param("unitRowid") Long unitRowid);

    @Query("select c from Contract c left join c.unit u where c.unit.unit_rowid=:unitRowid and c.enddate<current_date order by c.enddate desc ")
    List<Contract> findContractByUnitOldDesc(@Param("unitRowid") Long unitRowid);
}