package com.sjs.jsvill.service.contract;

import com.sjs.jsvill.dto.CarDTO;
import com.sjs.jsvill.dto.ContractDTO;
import com.sjs.jsvill.dto.OptionDTO;
import com.sjs.jsvill.dto.TenantDTO;
import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity._ContractType;

import java.util.List;

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

    //하나의 계약에 각종 리스트들을 넣어줄것임
    default ContractDTO entityToDTO(Contract contarct, List<CarDTO> carDTOList, List<TenantDTO> tenantDTOList, OptionDTO optionDTO) {
        ContractDTO contractDTO = ContractDTO.builder()
                .contractRowid(contarct.getContract_rowid())
                .unitRowid(contarct.getUnit().getUnit_rowid())
                ._contractTypeRowid(contarct.get_contracttype().get_contracttype_rowid())
                .startDate(contarct.getStartdate())
                .endDate(contarct.getEnddate())
                .deposit(contarct.getDeposit())
                .rentFee(contarct.getRentfee())
                .managementFees(contarct.getManagementfees())
                .paymentDay(contarct.getPaymentday())
                .carDTOList(carDTOList)
                .tenantDTOList(tenantDTOList)
                .optionDTO(optionDTO)
                .build();
        return contractDTO;
    }
//
//    //contract 여러개일 때
//    default List<ContractDTO> entitiesToDTO(List<Contarct> contarctList) {
//        List<ContractDTO> contractDTOList = new ArrayList<>();
//        if(!contarctList.isEmpty()) {
//            contractDTOList = contarctList.stream().map(contract -> ContractDTO.builder()
//                    .tenantRowid(contract.getUnit().getUnit_rowid())
//                    ._contracttypeRowid(contract.getContractType().get_contracttype_rowid())
//                    .title(contract.getTitle())
//                    .startdate(contract.getStartdate())
//                    .enddate(contract.getEnddate())
//                    .isprogressing(contract.getIsprogressing())
//                    .deposit(contract.getDeposit())
//                    .rentfee(contract.getRentfee())
//                    .managementfees(contract.getManagementfees())
//                    .paymentday(contract.getPaymentday())
//                    .build()
//            ).collect(Collectors.toList());
//        }
//
//        return contractDTOList;
//    }
}
