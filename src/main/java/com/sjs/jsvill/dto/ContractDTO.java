package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Contract;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContractDTO {
    private Long contractRowid;
    private Long unitRowid;
    private Long ContractTypeRowid;
    private String startdate; //view에서 name을 startDate로 하게되면 value값이 제대로 들어가지 않아서 이렇게 처리함
    private String enddate;
    private Long deposit; //보증금
    private Long rentFee; //월세
    private Long managementFees; //관리비
    private Long paymentDay; //납부일
    private Long dDay; //startDate-endDate

    @Builder.Default
    private List<TenantDTO> tenantDTOList = new ArrayList<>();
    //carDTOList 보이는거 자체는 contractDTO이지만, 사실 관계는 tenantDto 안에 있어야 하는데..
    @Builder.Default
    private List<CarDTO> carDTOList = new ArrayList<>();
    @Builder.Default
    private OptionDTO optionDTO = new OptionDTO();


    //하나의 계약에 각종 리스트들을 넣어줄것임
    public static ContractDTO entityToDTO(Contract contarct, List<TenantDTO> tenantDTOList, List<CarDTO> carDTOList, OptionDTO optionDTO) {
        ContractDTO contractDTO = ContractDTO.builder()
                .contractRowid(contarct.getContract_rowid())
                .unitRowid(contarct.getUnit().getUnit_rowid())
                .ContractTypeRowid(contarct.get_contracttype().get_contracttype_rowid())
                .startdate(contarct.getStartdate())
                .enddate(contarct.getEnddate())
                .deposit(contarct.getDeposit())
                .rentFee(contarct.getRentfee())
                .managementFees(contarct.getManagementfees())
                .paymentDay(contarct.getPaymentday())
                .tenantDTOList(tenantDTOList)
                .carDTOList(carDTOList)
                .optionDTO(optionDTO)
                .dDay(contarct.dDayOperator(contarct.getEnddate()))
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
