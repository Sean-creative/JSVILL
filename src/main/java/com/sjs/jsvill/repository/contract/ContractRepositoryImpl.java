package com.sjs.jsvill.repository.contract;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.QContract;
import com.sjs.jsvill.entity.QUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContractRepositoryImpl implements ContractRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Contract> findContractByUnitNewLimit(Long unitRowid, Pageable pageable) {
        QContract qContract = QContract.contract;
        // 형식 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        JPAQuery<Contract> query = queryFactory.selectFrom(qContract)
                .leftJoin(qContract.unit, QUnit.unit)
                .where(QUnit.unit.unit_rowid.eq(unitRowid)
                        .and(qContract.enddate.goe(LocalDate.now().format(formatter))))
                .orderBy(qContract.enddate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        return query.fetch();
    }

    @Override
    public List<Contract> findContractByUnitOld(Long unitRowid, Sort sortOrder) {
        return null;
    }

    @Override
    public List<Contract> findProgressContractsByUnits(List<Long> unitRowids) {
        return null;
    }
}
