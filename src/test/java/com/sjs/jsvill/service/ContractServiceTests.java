package com.sjs.jsvill.service;

import com.sjs.jsvill.dto.CarDTO;
import com.sjs.jsvill.dto.ContractDTO;
import com.sjs.jsvill.dto.OptionDTO;
import com.sjs.jsvill.dto.TenantDTO;
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
        carDTOList.add(CarDTO.builder().title("XM3").number("179너4679").phone("010-6780-1449").build());
        carDTOList.add(CarDTO.builder().title("타이칸").number("1111너1111").phone("010-1111-1111").build());

        ContractDTO contractDTO = ContractDTO.builder()
                .unitRowid(1L)
                .startDate("2022-03-08")
                .endDate("2022-03-30")
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
        Json.stringToJson(contractService.get(28L));
    }
}
