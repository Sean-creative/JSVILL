package com.sjs.jsvill.repository.contract;

import com.sjs.jsvill.entity.Contract;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepositoryCustom {

    //특정 호실의 2개의 계약 (진행중+바로다음 계약)을 가져옴
    List<Contract> findContractByUnitNewLimit(Long unitRowid, Pageable pageable);

    List<Contract> findContractByUnitOld(Long unitRowid, Sort sortOrder);

    //unitRowids를 통해 현재 진행중인 계약의 리스트를 가져옴
    List<Contract> findProgressContractsByUnits(List<Long> unitRowids);
}
