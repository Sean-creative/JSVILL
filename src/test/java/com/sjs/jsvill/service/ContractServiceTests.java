package com.sjs.jsvill.service;

import com.sjs.jsvill.dto.CarDTO;
import com.sjs.jsvill.dto.ContractDTO;
import com.sjs.jsvill.dto.OptionDTO;
import com.sjs.jsvill.dto.TenantDTO;
import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.service.contract.ContractService;
import com.sjs.jsvill.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ContractServiceTests {

    @Autowired
    private ContractService contractService;

    @Test
    public void testRegister() {
        List<TenantDTO> tenantDTOList = new ArrayList<>();
        List<CarDTO> carDTOList = new ArrayList<>();
        OptionDTO optionDTO = OptionDTO.builder().optionList(Arrays.asList("전자레인지", "압력밥솥", "냉장고")).build();
        tenantDTOList.add(TenantDTO.builder()
                .title("김아빠")
                .phone("010-6780-1449")
                .isContractor(true)
                .livingType(20L)
                .build());
        tenantDTOList.add(TenantDTO.builder()
                .title("김엄마")
                .phone("010-1111-1111")
                .isContractor(false)
                .livingType(20L)
                .build());
        carDTOList.add(CarDTO.builder().title("XM3").number("179너4679").build());
        carDTOList.add(CarDTO.builder().title("타이칸").number("1111너1111").build());

        ContractDTO contractDTO = ContractDTO.builder()
                .unitRowid(1L)
                .startdate("2022-03-08")
                .enddate("2022-03-30")
                .deposit(1000L)
                .rentFee(30L)
                .managementFees(5L)
                .paymentDay(1L)
                .tenantDTOList(tenantDTOList)
                .carDTOList(carDTOList)
//                .optionDTO(optionDTO)
                .ContractTypeRowid(10L)
                .build();
        contractService.register(contractDTO);
    }

    @Test
    public void get() {
        ContractDTO contractDTO = contractService.getDTO(30L);
        Json.stringToJson(contractService.getDTO(30L), "test-get");
    }

    @Test
    public void remove() {
        contractService.remove(1L);
    }

    @Test
    public void dDayOperator() {
        Contract contract = Contract.builder().contract_rowid(1L).build();
        System.out.println(contract.dDayOperator("2022-05-03"));
    }

    @Test
    public void getPreviousContractHistoryList() {
        System.out.println("contractService.getPreviousContractHistoryList(14L) :" + contractService.getPreviousContractHistoryList(14L, true));
    }
}
