package com.sjs.jsvill.service.contract;

import com.sjs.jsvill.dto.ContractDTO;
import com.sjs.jsvill.dto.OptionDTO;
import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Option;
import com.sjs.jsvill.entity.Tenant;
import com.sjs.jsvill.repository.ContractRepository;
import com.sjs.jsvill.repository.OptionRepository;
import com.sjs.jsvill.repository.TenantRepository;
import com.sjs.jsvill.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final TenantRepository tenantRepository;
    private final ContractRepository contractRepository;
    private final OptionRepository optionRepository;

    @Override
    public String phoneCheck(List<String> phoneList) {
        //1. 해당 폰번호가 DB에 있는 검사를 한다
        Tenant tenant = tenantRepository.findAllByPhoneIn(phoneList);
        //2. 중복된 폰번호를 리턴하거나, 중복된 폰번호가 없으면 공백 리턴
        if(tenant == null) return "";
        else return tenant.getPhone();
    }

    @Override
    @Transactional
    //계약은 트랜잭션으로 이루어져 한다. 뭐 하나라도 잘못되면 롤백!!
    public void register(ContractDTO contractDTO) {
        Json.stringToJson(contractDTO);
//        1. 계약 등록
        Contract contract = contractRepository.save(dtoToEntity(contractDTO));
//        1-1. 계약R -> 옵션 등록
        log.info("contract.getContract_rowid() : " + contract.getContract_rowid());
        OptionDTO optionDTO = contractDTO.getOptionDTO();
        log.info("optionDTO : " + optionDTO);
        Option option = OptionDTO.DTOToEntity(optionDTO, contract.getContract_rowid());
        log.info("optionRepository.save(option)" + optionRepository.save(option));


        //        2. 세입자 등록
//        2-1. 세입자R -> 차량 등록

    }
}