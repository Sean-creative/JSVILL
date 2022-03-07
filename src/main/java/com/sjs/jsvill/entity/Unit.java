package com.sjs.jsvill.entity;

import com.sjs.jsvill.dto.ContractDTO;
import com.sjs.jsvill.dto.UnitDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="unit")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "group")
public class Unit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unit_rowid;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "group_rowid", nullable = false)
    private Group group;

    @Column(length = 100, nullable = false)
    private String addr2; // 호수

    @Column(nullable = false)
    private Long deposit; //보증금

    @Column(nullable = false)
    private Long rentfee; //월세

    @Column(nullable = false)
    private Long managementfees; //관리비

    @Column(length = 100, nullable = false)
    private Long paymentday; //납부일

    @Column(nullable = false)
    private String memo; // 메모 (선택적)

    public void changeAddr2(String addr2) { this.addr2 = addr2; }
    public void changeDeposit(Long deposit) { this.deposit = deposit; }
    public void changeRentfee(Long rentfee) { this.rentfee = rentfee; }
    public void changeManagemnetfees(Long managementfees) { this.managementfees = managementfees; }
    public void changePaymentday(Long paymentday) { this.paymentday = paymentday; }
    public void changeMemo(String memo) { this.memo = memo; }

    //단순히 호실만 다룰 때
    public static UnitDTO entityToDTO(Unit unit) {
        UnitDTO unitDTO = UnitDTO.builder()
                .unitRowid(unit.getUnit_rowid())
                .groupRowid(unit.getGroup().getGroup_rowid())
                .addr2(unit.getAddr2())
                .deposit(unit.getDeposit())
                .rentFee(unit.getRentfee())
                .managementFees(unit.getManagementfees())
                .paymentDay(unit.getPaymentday())
                .memo(unit.getMemo())
                .build();
        return unitDTO;
    }

    //여기에 재료를 쏟아 부으면 너무 과부하가 걸릴까 걱정 -> 역할을 분리하고자 unit빼고는 DTO를 받는게 낫겠다.
    public static  UnitDTO entityToDTOWithContract(Unit unit, List<ContractDTO> contractDTOList) {
        Group group = unit.getGroup();
        List<String> optionList = new ArrayList<>();
        if(!contractDTOList.isEmpty()){
            optionList = contractDTOList.get(0).getOptionDTO().getOptionList();
        }

        UnitDTO unitDTO = UnitDTO.builder()
                .unitRowid(unit.getUnit_rowid())
                .groupRowid(unit.getGroup().getGroup_rowid())
                .addr2(unit.getAddr2())
                .deposit(unit.getDeposit())
                .rentFee(unit.getRentfee())
                .managementFees(unit.getManagementfees())
                .paymentDay(unit.getPaymentday())
                .memo(unit.getMemo())
                .groupTitle(group.getTitle())
                .groupAddr(group.getAddr1())
                .contractDTOList(contractDTOList)
                .optionList(optionList)
                .build();
        return unitDTO;
    }
}