package com.sjs.jsvill.repository;

import com.sjs.jsvill.repository.unit.UnitRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UnitRepositoryTests {

    @Autowired
    UnitRepository unitRepository;

    @Test
    public void testClass() {
        System.out.println(unitRepository.getClass().getName());
    }
}
