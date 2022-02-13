package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
//    @Query("select o from Option o where o.contract.contract_rowid=:contract_rowid")
//    List<Option> findByContract(@Param("contract_rowid") Long contract_rowid);
}
