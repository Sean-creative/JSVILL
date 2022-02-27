package com.sjs.jsvill.entity;

import com.sjs.jsvill.dto.CarDTO;
import com.sjs.jsvill.dto.ContractDTO;
import com.sjs.jsvill.dto.OptionDTO;
import com.sjs.jsvill.dto.TenantDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="contract")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"unit", "_contracttype"})
public class Contract extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contract_rowid;

    @ManyToOne
    @JoinColumn(name = "unit_rowid")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "_contracttype_rowid")
    private _ContractType _contracttype;

    @Column(length = 100, nullable = false)
    private String startdate;

    @Column(length = 100, nullable = false)
    private String enddate;

    @Column(nullable = false)
    private Long deposit;

    @Column(nullable = false)
    private Long rentfee;

    @Column(nullable = false)
    private Long managementfees;

    @Column(length = 100, nullable = false)
    private Long paymentday;


    //하나의 계약에 각종 리스트들을 넣어줄것임
    public static ContractDTO entityToDTO(Contract contarct, List<CarDTO> carDTOList, List<TenantDTO> tenantDTOList, OptionDTO optionDTO) {
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