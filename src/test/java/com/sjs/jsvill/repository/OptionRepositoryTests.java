package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.sean.Contract;
import com.sjs.jsvill.entity.sean.Option;
import com.sjs.jsvill.repository.sean.OptionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.LongStream;

@SpringBootTest
public class OptionRepositoryTests {

    @Autowired
    private OptionRepository optionRepository;

    @Test
    public void save() {
        Contract contarct = Contract.builder().contract_rowid(1L).build();
        LongStream.rangeClosed(1, 3).forEach(i -> {
            Option option = Option.builder()
                    .contract(contarct)
                    .optionList("침대,냉장고,전자레인지")
                    .build();
            System.out.println(optionRepository.save(option));
        });
    }

    @Test
    public void findByContract() {
        Option option = optionRepository.findByContract(Contract.builder().contract_rowid(25L).build());
        System.out.println("option : " + option);
    }
}
