package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Unit;
import com.sjs.jsvill.entity._GroupType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class UnitRepositoryTests {

    @Autowired
    UnitRepository unitRepository;

    @Test
    public void testRegister() {

        _GroupType groupType = _GroupType.builder().build();
        Group group = Group.builder().groupType(groupType).build();

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Unit unit = Unit.builder()
                    .group(group)
                    .addr2("addr2" + i)
                    .build();

            System.out.println(unit.toString());
            System.out.println(unitRepository.(unit));
        });
    }
}
