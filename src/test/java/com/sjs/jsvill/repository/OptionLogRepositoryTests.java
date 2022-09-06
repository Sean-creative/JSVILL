package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.OptionLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.LongStream;

@SpringBootTest
public class OptionLogRepositoryTests {

    @Autowired
    private OptionLogRepository optionLogRepository;

    @Test
    public void save() {
        Contract contarct = Contract.builder().contract_rowid(83L).build();
        LongStream.rangeClosed(1, 3).forEach(i -> {
            OptionLog optionLog = OptionLog.builder()
                    .contract(contarct)
                    .optionList("침대,냉장고,전자레인지")
                    .build();
            System.out.println(optionLogRepository.save(optionLog));
        });
    }

}
