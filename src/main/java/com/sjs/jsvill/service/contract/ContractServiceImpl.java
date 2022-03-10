package com.sjs.jsvill.service.contract;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {


    @Override
    public void phoneCheck(List<String> phoneList) {
        //1. 리스트로 받은 폰번호를 돌면서 검사를 한다.
        for(String phone : phoneList) {
            //2. 해당 폰번호가 DB에 있는 검사를 한다
        }
    }

//    private final ContractRepository contractRepository;

//    @Override
//    public void register(ContractDTO contractDTO) {
//
////        contractRepository.save(contractDTO);
//    }
}
