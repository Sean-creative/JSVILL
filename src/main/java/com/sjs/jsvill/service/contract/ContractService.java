package com.sjs.jsvill.service.contract;

import com.sjs.jsvill.dto.ContractDTO;
import com.sjs.jsvill.entity.Contract;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface ContractService {
//    Long register(GroupDTO dto);
//    List<GroupDTO> getList(Long member_rowid);
//    Long remove(Long group_rowid);
//    Group get(Long group_rowid);
//    void modify(GroupDTO groupDTO);

//    default Group dtoToEntity(GroupDTO dto) {
//        _GroupType groupType = _GroupType.builder()._grouptype_rowid(dto.getGroupType_rowid()).build();
//        Group group = Group.builder()
//                .group_rowid(dto.getGroup_rowid())
//                .groupType(groupType)
//                .title(dto.getTitle())
//                .addr1(dto.getAddr1())
//                .postNum(dto.getPostNum())
//                .memo(dto.getMemo())
//                .completiondate(dto.getCompletiondate())
//                .build();
//        return group;
//    }

//    default GroupDTO entityToDTO(Group group) {
//        GroupDTO groupDTO = GroupDTO.builder()
//                .group_rowid(group.getGroup_rowid())
//                .groupType_rowid(group.getGroupType().get_grouptype_rowid())
//                .title(group.getTitle())
//                .addr1(group.getAddr1())
//                .postNum(group.getPostNum())
//                .memo(group.getMemo())
//                .build();
//        return groupDTO;
//    }

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
