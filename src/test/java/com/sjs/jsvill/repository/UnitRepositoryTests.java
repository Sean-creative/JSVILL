package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Unit;
import com.sjs.jsvill.entity._GroupType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.LongStream;

@SpringBootTest
public class UnitRepositoryTests {

    @Autowired
    private UnitRepository unitRepository;

    @Test
    public void testRegister() {
        _GroupType groupType = _GroupType.builder()._grouptype_rowid(10L).build();
        Group group = Group.builder().group_rowid(1L).groupType(groupType).build();

        LongStream.rangeClosed(1, 3).forEach(i -> {
            Unit unit = Unit.builder()
                    .group(group)
                    .addr2("addr2" + i)
                    .deposit(i)
                    .rentfee(i)
                    .managementfees(i)
                    .paymentday(i)
                    .memo("memo" + i)
                    .build();
            System.out.println(unitRepository.save(unit));
        });
    }
}
