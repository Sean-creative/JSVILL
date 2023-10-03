package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Unit;
import com.sjs.jsvill.entity.defaultType._ContractType;
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
    @Builder.Default
    private List<PhotoDTO> photoDTOList = new ArrayList<>();

    /**계약 종류가 '자가'라면 보증금/월세/관리비를 0으로 만듦 */
    public static void realEstateOwner(ContractDTO contractDTO) {
        if(contractDTO.getContractTypeRowid()==40L) {
            contractDTO.setDeposit(0L);
            contractDTO.setRentFee(0L);
            contractDTO.setManagementFees(0L);
            contractDTO.setPaymentDay(0L);
        }
    }

    public static Contract dtoToEntity(ContractDTO contractDTO) {
        //계약을 등록 하기위해 뷰에서 받은 값을 엔티티로 바꿔보자
        // 1. tenant에 insert
        // 2. car를 등록했다면 car에 insert
        // 3. contract에 insert
        // 4. option에 insert

        realEstateOwner(contractDTO);
        return Contract.builder()
                .unit(Unit.builder().unit_rowid(contractDTO.getUnitRowid()).build())
                ._contracttype(_ContractType.builder()._contracttype_rowid(contractDTO.getContractTypeRowid()).build())
                .startdate(contractDTO.getStartdate())
                .enddate(contractDTO.getEnddate())
                .deposit(contractDTO.getDeposit())
                .rentfee(contractDTO.getRentFee())
                .managementfees(contractDTO.getManagementFees())
                .paymentday(contractDTO.getPaymentDay())
                .build();
    }
}
