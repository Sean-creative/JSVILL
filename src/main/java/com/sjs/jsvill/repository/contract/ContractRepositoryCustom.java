package com.sjs.jsvill.repository.contract;

import com.sjs.jsvill.entity.Contract;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepositoryCustom {

    //특정 호실의 2개의 계약 (진행중+바로다음 계약)을 가져온다.
    List<Contract> findContractByUnitNewLimit(Long unitRowid, Pageable pageable);

//    @Query("SELECT c FROM Contract c LEFT JOIN c.unit u WHERE u.unit_rowid = :unitRowid AND c.enddate >= CURRENT_DATE ORDER BY c.enddate")
//    List<Contract> findContractByUnitNewLimit(@Param("unitRowid") Long unitRowid, @Param("pageable") Pageable pageable);

    @Query("select c from Contract c left join c.unit u where c.unit.unit_rowid=:unitRowid and c.enddate<current_date order by c.enddate")
    List<Contract> findContractByUnitOld(@Param("unitRowid") Long unitRowid, Sort sortOrder);

    //unitRowids를 통해 현재 진행중인 계약의 리스트를 가져옴
    @Query("SELECT c FROM Contract c LEFT JOIN c.unit u WHERE c.enddate >= CURRENT_DATE And c.startdate <= CURRENT_DATE AND c.unit.unit_rowid IN :unitRowids")
    List<Contract> findProgressContractsByUnits(@Param("unitRowids") List<Long> unitRowids);
}
