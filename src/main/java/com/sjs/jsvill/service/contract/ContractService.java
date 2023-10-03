package com.sjs.jsvill.service.contract;

import com.sjs.jsvill.controller.util.UserDuplicateCheck;
import com.sjs.jsvill.dto.ContractDTO;
import com.sjs.jsvill.dto.PreviousContractHistoryDTO;
import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Unit;
import com.sjs.jsvill.entity.defaultType._ContractType;

import java.util.List;

public interface ContractService {
    void register(ContractDTO contractDTO);

    void remove(Long contract_rowid);

    ContractDTO getDTO(Long contractRowid);

    Contract get(Long contractRowid);

    void modify(ContractDTO contractDTO);

    String phoneCheck(List<UserDuplicateCheck> duplicateCheckList);

    void expire(Long contractRowid);
    PreviousContractHistoryDTO getPreviousContractHistoryList(Long unitRowid, boolean isAsc);
}
