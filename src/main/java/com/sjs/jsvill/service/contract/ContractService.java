package com.sjs.jsvill.service.contract;

import com.sjs.jsvill.dto.CarDTO;
import com.sjs.jsvill.dto.ContractDTO;
import com.sjs.jsvill.dto.TenantDTO;
import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Unit;
import com.sjs.jsvill.entity._ContractType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface ContractService {
    void register(ContractDTO contractDTO);
//    List<GroupDTO> getList(Long member_rowid);
//    Long remove(Long group_rowid);
//    Group get(Long group_rowid);
//    void modify(GroupDTO groupDTO);

    default Contract dtoToEntity(ContractDTO contractDTO) {
        Unit unit = Unit.builder().unit_rowid(contractDTO.getUnit_rowid()).build();
        _ContractType contractType = _ContractType.builder()._contracttype_rowid(contractDTO.get_contracttype_rowid()).build();
        Contract contract = Contract.builder()
                .contract_rowid(contractDTO.getContract_rowid())
                .unit(unit)
                .contractType(contractType)
                .title(contractDTO.getTitle())
                .startdate(contractDTO.getStartdate())
                .enddate(contractDTO.getEnddate())
                .isprogressing(contractDTO.getIsprogressing())
                .deposit(contractDTO.getDeposit())
                .rentfee(contractDTO.getRentfee())
                .managementfees(contractDTO.getManagementfees())
                .paymentday(contractDTO.getPaymentday())
                .build();
        return contract;
    }

    default ContractDTO entityToDTO(Contract contract, List<CarDTO> carDTOList, List<TenantDTO> tenantDTOList) {
        ContractDTO contractDTO = ContractDTO.builder()
                .contract_rowid(contract.getContract_rowid())
                .unit_rowid(contract.getUnit().getUnit_rowid())
                ._contracttype_rowid(contract.getContractType().get_contracttype_rowid())
                .title(contract.getTitle())
                .startdate(contract.getStartdate())
                .enddate(contract.getEnddate())
                .isprogressing(contract.getIsprogressing())
                .deposit(contract.getDeposit())
                .rentfee(contract.getRentfee())
                .managementfees(contract.getManagementfees())
                .paymentday(contract.getPaymentday())
                .carDTOList(carDTOList)
                .tenantDTOList(tenantDTOList)
                .build();
        return contractDTO;
    }

    //contract 여러개일 때
    default List<ContractDTO> entitiesToDTO(List<Contract> contractList) {

        List<ContractDTO> contractDTOList = new ArrayList<>();
        if(!contractList.isEmpty()) {
            contractDTOList = contractList.stream().map(contract -> ContractDTO.builder()
                    .unit_rowid(contract.getUnit().getUnit_rowid())
                    ._contracttype_rowid(contract.getContractType().get_contracttype_rowid())
                    .title(contract.getTitle())
                    .startdate(contract.getStartdate())
                    .enddate(contract.getEnddate())
                    .isprogressing(contract.getIsprogressing())
                    .deposit(contract.getDeposit())
                    .rentfee(contract.getRentfee())
                    .managementfees(contract.getManagementfees())
                    .paymentday(contract.getPaymentday())
                    .build()
            ).collect(Collectors.toList());
        }

        return contractDTOList;
    }
}
