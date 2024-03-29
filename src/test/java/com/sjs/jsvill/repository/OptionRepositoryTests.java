package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Option;
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
    public void modify() {
        Contract contarct = Contract.builder().contract_rowid(83L).build();
        Option option = Option.builder()
                .contract(contarct)
                .optionList("침대,냉장고,전자레인지")
                .build();
        option = optionRepository.save(option);
        option.changeOptionList("킁킁,캉캉");
        optionRepository.save(option);
    }

    @Test
    public void findByContract() {
//        Option option = optionRepository.findByContract(Contract.builder().contract_rowid(25L).build());
        Option option = optionRepository.findByContract(null);
        System.out.println("option : " + option);
    }
}
