package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {

    //옵션이 하나도 없다면? 이걸 쿼리로 했어야 하는데, 괜히 끌고 들어와서 고생이네
    @Query("select o from Option o where o.contract.contract_rowid=:contract_rowid order by o.regDate desc")
    List<Option> findByContract(@Param("contract_rowid") Long contract_rowid);
}
