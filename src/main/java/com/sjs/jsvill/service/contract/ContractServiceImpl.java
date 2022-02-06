package com.sjs.jsvill.service.contract;

import com.sjs.jsvill.dto.ContractDTO;
import com.sjs.jsvill.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Override
    public void register(ContractDTO contractDTO) {

//        contractRepository.save(contractDTO);
    }
}
