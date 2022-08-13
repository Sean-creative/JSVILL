package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.sean.Contract;
import com.sjs.jsvill.entity.sean.Unit;
import com.sjs.jsvill.entity.sub._ContractType;
import com.sjs.jsvill.repository.sean.ContractRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

@SpringBootTest
public class ContractRepositoryTests {

    @Autowired
    private ContractRepository contractRepository;

    @Test
    public void testRegister() {
        Unit unit = Unit.builder().unit_rowid(1L).build();
        _ContractType contractType = _ContractType.builder()._contracttype_rowid(10L).build();

        LongStream.rangeClosed(1, 3).forEach(i -> {
            Contract contarct = Contract.builder()
                    .unit(unit)
                    ._contracttype(contractType)
                    .startdate("startdate" + i)
                    .enddate("enddate" + i)
                    .deposit(i)
                    .rentfee(i)
                    .managementfees(i)
                    .paymentday(i)
                    .build();
            System.out.println(contractRepository.save(contarct));
        });
    }

    @Test
    public void findContarctByUnitNotOld() {
        List<Contract> result = contractRepository.findContarctByUnitNotOld(1L);
        System.out.println("result.length : " + result.size());

        result.forEach(i -> System.out.println(i));
    }

    @Test
    public void findById(){
        Optional<Contract> contract = contractRepository.findById(27L);
        System.out.println(contract.get().getUnit());
    }

    @Test
    public void getBtId() {
        System.out.println(contractRepository.getById(27L));
    }

    @Test
    public void deleteById() {
        contractRepository.deleteById(3L);
    }

    @Test
    public void findAllByUnit() {
        System.out.println("contractRepository.findAllByUnit(1L) : " + contractRepository.findContractByUnitOld(14L));
    }

}
