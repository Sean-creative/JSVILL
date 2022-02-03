package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Unit;
import com.sjs.jsvill.entity._ContractType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.LongStream;

@SpringBootTest
public class ContractRepositoryTests {

    @Autowired
    private ContractRepository contractRepository;

    @Test
    public void testRegister() {
//        _GroupType groupType = _GroupType.builder()._grouptype_rowid(10L).build();
//        Group group = Group.builder().group_rowid(1L).groupType(groupType).build();
//        Unit unit = Unit.builder().group(group).build();
        Unit unit = Unit.builder().unit_rowid(1L).build();
        _ContractType contractType = _ContractType.builder()._contracttype_rowid(10L).build();

        LongStream.rangeClosed(1, 3).forEach(i -> {
            Contract contract = Contract.builder()
                    .unit(unit)
                    .contractType(contractType)
                    .title("title" + i)
                    .startdate("startdate" + i)
                    .enddate("enddate" + i)
                    .isprogressing(i==1)
                    .deposit(i)
                    .rentfee(i)
                    .managementfees(i)
                    .paymentday(i)
                    .build();
            System.out.println(contractRepository.save(contract));
        });
    }

    @Test
    public void findByUnit() {
        List<Contract> result = contractRepository.findByUnit(1L);
        System.out.println("result.length : " + result.size());
        for (Contract contract : result) {
            System.out.println("contract : " + contract);
        }
    }
}
