package com.sjs.jsvill.service.tenant;

import com.sjs.jsvill.repository.ContractRepository;
import com.sjs.jsvill.service.contract.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class TenantServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

//    @Override
//    public void register(ContractDTO contractDTO) {
//
////        contractRepository.save(contractDTO);
//    }
}
