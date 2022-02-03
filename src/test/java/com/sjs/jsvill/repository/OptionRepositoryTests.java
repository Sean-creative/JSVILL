package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Option;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.LongStream;

@SpringBootTest
public class OptionRepositoryTests {

    @Autowired
    private OptionRepository optionRepository;

    @Test
    public void testRegister() {
        Contract contract = Contract.builder().contract_rowid(1L).build();
        LongStream.rangeClosed(1, 3).forEach(i -> {
            Option option = Option.builder()
                    .contract(contract)
                    .title("title" + i)
                    .build();
            System.out.println(optionRepository.save(option));
        });
    }

    @Test
    public void findByContract() {
        List<Option> result = optionRepository.findByContract(1L);
        System.out.println("result.length : " + result.size());
        for (Option option : result) {
            System.out.println("option : " + option);
        }
    }

}
