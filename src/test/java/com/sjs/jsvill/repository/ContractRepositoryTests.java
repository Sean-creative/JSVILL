package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Unit;
import com.sjs.jsvill.entity.defaultType._ContractType;
import com.sjs.jsvill.repository.contract.ContractRepository;
import com.sjs.jsvill.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StopWatch;

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
    public void findContractByUnitNewLimit() {
        // 시간 측정 시작
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        //특정 호실의 2개의 계약 (진행중+바로다음 계약)을 가져온다.
        List<Contract> result = contractRepository.findContractByUnitNewLimit(17L, PageRequest.of(0, 2));

        // 시간 측정 종료 및 로그 출력
        stopWatch.stop();
        System.out.println("Execution time: " + stopWatch.getTotalTimeMillis() + "ms");
        result.forEach(System.out::println);
    }

    @Test
    public void findById(){
        Optional<Contract> contract = contractRepository.findById(11L);
        Json.stringToJson(contract.get(), "findById Test");
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
    public void findContractByUnitOld() {
        Long unitRowid = 17L;
        System.out.println("contractRepository.findAllByUnit(unitRowid) : " + contractRepository.findContractByUnitOld(unitRowid, Sort.by(Sort.Direction.ASC, "enddate")));
    }

    @Test
    public void findProgressContractsByUnits() {
        List<Long> unitRowids = List.of(12L, 17L );
        List<Contract> contractList = contractRepository.findProgressContractsByUnits(unitRowids);
        System.out.println("size :" + contractList.size());
        Json.stringToJson(contractList, "findProgressContractsByUnits Test");
    }
}
