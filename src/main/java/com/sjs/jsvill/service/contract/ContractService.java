package com.sjs.jsvill.service.contract;

import com.sjs.jsvill.dto.ContractDTO;
import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity._ContractType;

public interface ContractService {
//    void register(ContractDTO contractDTO);
//    List<GroupDTO> getList(Long member_rowid);
//    Long remove(Long group_rowid);
//    Group get(Long group_rowid);
//    void modify(GroupDTO groupDTO);

    default Contract dtoToEntity(ContractDTO contractDTO) {
        //계약을 등록 하기위해 뷰에서 받은 값을 엔티티로 바꿔보자
        // 1. tenant에 insert
        // 2. car를 등록했다면 car에 insert
        // 3. contract에 insert
        // 4. option에 insert

//        Unit unit = Unit.builder().unit_rowid(contractDTO.gettenantRowid()).build();
        _ContractType contractType = _ContractType.builder()._contracttype_rowid(contractDTO.get_contractTypeRowid()).build();
        Contract contract = Contract.builder()
                ._contracttype(contractType)
                .startdate(contractDTO.getStartDate())
                .enddate(contractDTO.getEndDate())
                .deposit(contractDTO.getDeposit())
                .rentfee(contractDTO.getRentFee())
                .managementfees(contractDTO.getManagementFees())
                .paymentday(contractDTO.getPaymentDay())
                .build();
        return contract;
    }


}
