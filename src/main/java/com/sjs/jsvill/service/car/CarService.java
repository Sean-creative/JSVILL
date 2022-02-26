package com.sjs.jsvill.service.car;

import com.sjs.jsvill.dto.CarDTO;
import com.sjs.jsvill.entity.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface CarService {

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

    default List<CarDTO> entitiesToDTO(List<Car> carList) {
        List<CarDTO> carDTOList = new ArrayList<>();
        if (!carList.isEmpty()) {
            carDTOList = carList.stream().map(car -> CarDTO.builder()
                    .title(car.getTitle())
                    .number(car.getNumber())
                    .build()
            ).collect(Collectors.toList());
        }
        return carDTOList;
    }
}
