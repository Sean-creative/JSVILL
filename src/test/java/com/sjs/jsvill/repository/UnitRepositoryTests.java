package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.sean.Group;
import com.sjs.jsvill.entity.sean.Unit;
import com.sjs.jsvill.entity.sub._GroupType;
import com.sjs.jsvill.repository.sean.UnitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.stream.LongStream;

@SpringBootTest
public class UnitRepositoryTests {

    @Autowired
    private UnitRepository unitRepository;

    @Test
    public void register() {
        _GroupType groupType = _GroupType.builder()._grouptype_rowid(10L).build();
        Group group = Group.builder().group_rowid(1L)._grouptype(groupType).build();

        LongStream.rangeClosed(1, 3).forEach(i -> {
            Unit unit = Unit.builder()
                    .group(group)
                    .detailaddr("detailAddr" + i)
                    .memo("memo" + i)
                    .build();
            System.out.println(unitRepository.save(unit));
        });
    }

    @Transactional
    @Test
    public void delete() {
        System.out.println(unitRepository.deleteByGroupRowid(8L));
    }
}
