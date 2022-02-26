package com.sjs.jsvill.service.option;

import com.sjs.jsvill.dto.OptionDTO;
import com.sjs.jsvill.entity.Option;

public interface OptionService {

//    default Contract dtoToEntity(ContractDTO contractDTO) {
//        _ContractType contractType = _ContractType.builder()._contracttype_rowid(contractDTO.get_contractTypeRowid()).build();
//        Contract contract = Contract.builder()
//                ._contracttype(contractType)
//                .startdate(contractDTO.getStartDate())
//                .enddate(contractDTO.getEndDate())
//                .deposit(contractDTO.getDeposit())
//                .rentfee(contractDTO.getRentFee())
//                .managementfees(contractDTO.getManagementFees())
//                .paymentday(contractDTO.getPaymentDay())
//                .build();
//        return contract;
//    }

    default OptionDTO entityToDTO(Option option) {
        OptionDTO optionDTO = new OptionDTO();
        if (option != null) {
            optionDTO.setOptionRowid(option.getOption_rowid());
            optionDTO.setOptionList(option.csvToList(option.getOptionList()));
        }
        return optionDTO;
    }
}
