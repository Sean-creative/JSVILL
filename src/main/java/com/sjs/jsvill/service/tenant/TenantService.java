package com.sjs.jsvill.service.tenant;

import com.sjs.jsvill.dto.TenantDTO;
import com.sjs.jsvill.entity.Tenant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface TenantService {


//    default Tenant dtoToEntity(ContractDTO contractDTO) {
////        Unit unit = Unit.builder().unit_rowid(contractDTO.gettenantRowid()).build();
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

//    default ContractDTO entityToDTO(Contract contarct, List<CarDTO> carDTOList, List<TenantDTO> tenantDTOList) {
//        ContractDTO contractDTO = ContractDTO.builder()
//                .contractRowid(contarct.getContract_rowid())
//                .unitRowid(contarct.getUnit().getUnit_rowid())
//                ._contractTypeRowid(contarct.get_contracttype().get_contracttype_rowid())
//                .startDate(contarct.getStartdate())
//                .endDate(contarct.getEnddate())
//                .deposit(contarct.getDeposit())
//                .rentFee(contarct.getRentfee())
//                .managementFees(contarct.getManagementfees())
//                .paymentDay(contarct.getPaymentday())
//
//                .carDTOList(carDTOList)
//                .tenantDTOList(tenantDTOList)
//                .build();
//        return contractDTO;
//    }
//

    default List<TenantDTO> entitiesToDTO(List<Tenant> tenantList) {
        List<TenantDTO> tenantDTOList = new ArrayList<>();
        if(!tenantList.isEmpty()) {
            tenantDTOList = tenantList.stream().map(tenant -> TenantDTO.builder()
                    .title(tenant.getTitle())
                    .phone(tenant.getPhone())
                    .isContractor(tenant.getIscontractor())
                    .build()
            ).collect(Collectors.toList());
        }
        return tenantDTOList;
    }
}
