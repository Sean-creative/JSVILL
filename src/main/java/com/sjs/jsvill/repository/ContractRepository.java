package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    //특정 호실의 2개의 계약 (진행중+바로다음 계약)을 가져온다.
    @Query(nativeQuery = true, value = "SELECT * FROM \n" +
            "contract c left JOIN unit u ON c.unit_rowid = u.unit_rowid\n" +
            "where c.unit_rowid=:unitRowid and c.enddate>=CURDATE() \n" +
            "order by c.enddate LIMIT 2")
    List<Contract> findContarctByUnitNotOldLimit2(@Param("unitRowid") Long unitRowid);

    //특정 호실의 지금 진행중인 계약을 가져온다.
    @Query(nativeQuery = true, value = "SELECT * FROM \n" +
            "contract c left JOIN unit u ON c.unit_rowid = u.unit_rowid\n" +
            "where c.unit_rowid=:unitRowid and c.enddate>=CURDATE() \n" +
            "order by c.enddate LIMIT 1")
    Contract findContarctByUnitNotOldLimit1(@Param("unitRowid") Long unitRowid);

    @Query("select c from Contract c left join c.unit u where c.unit.unit_rowid=:unitRowid and c.enddate<current_date order by c.enddate asc")
    List<Contract> findContractByUnitOldAsc(@Param("unitRowid") Long unitRowid);

    @Query("select c from Contract c left join c.unit u where c.unit.unit_rowid=:unitRowid and c.enddate<current_date order by c.enddate desc ")
    List<Contract> findContractByUnitOldDesc(@Param("unitRowid") Long unitRowid);

    //조회해야하는 호실 rowid를 한번에 던져서 현재 진행중인 계약의 리스트를 가져옴
    @Query("SELECT c FROM Contract c LEFT JOIN c.unit u WHERE c.enddate >= CURRENT_DATE And c.startdate <= CURRENT_DATE AND c.unit.unit_rowid IN :unitRowids")
    List<Contract> findProgressContractsByUnits(@Param("unitRowids") List<Long> unitRowids);
}
