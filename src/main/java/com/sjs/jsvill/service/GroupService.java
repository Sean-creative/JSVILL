package com.sjs.jsvill.service;

import com.sjs.jsvill.dto.GroupDTO;
import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Unit;
import com.sjs.jsvill.entity._GroupType;

import java.util.List;
import java.util.stream.Collectors;

public interface GroupService {
    Long register(GroupDTO dto);
    List<GroupDTO> getList(Long member_rowid);

    //파라미터로 받는건 DTO인데 -> DB에 접근하는 데이터는 엔티티로 바꿔줘야함
    default Group dtoToEntity(GroupDTO dto) {
        _GroupType groupType = _GroupType.builder()._grouptype_rowid(dto.getGroupType_rowid()).build();

        Group group = Group.builder()
                .group_rowid(dto.getGroup_rowid())
                .groupType(groupType)
                .title(dto.getTitle())
                .addr1(dto.getAddr1())
                .postnum(dto.getPostnum())
                .memo(dto.getMemo())
                .completiondate(dto.getCompletiondate())
                .build();
        return group;
    }

    default GroupDTO entityToDTO(Group group) {
        GroupDTO groupDTO = GroupDTO.builder()
                .group_rowid(group.getGroup_rowid())
                .groupType_rowid(group.getGroupType().get_grouptype_rowid())
                .title(group.getTitle())
                .addr1(group.getAddr1())
                .postnum(group.getPostnum())
                .memo(group.getMemo())
                .build();
        return groupDTO;
    }

    //unit이 여러개일 때
    default GroupDTO entitiesToDTO(Group group, List<Unit> unitList) {
        GroupDTO groupDTO = GroupDTO.builder()
                .group_rowid(group.getGroup_rowid())
                .groupType_rowid(group.getGroupType().get_grouptype_rowid())
                .title(group.getTitle())
                .addr1(group.getAddr1())
                .postnum(group.getPostnum())
                .memo(group.getMemo())
                .completiondate(group.getCompletiondate())
                .build();

        if(unitList.get(0) != null) {
            List<UnitDTO> unitDTOList = unitList.stream().map(unit -> UnitDTO.builder()
                    .unit_rowid(unit.getUnit_rowid())
                    .group_rowid(unit.getGroup().getGroup_rowid())
                    .addr2(unit.getAddr2())
                    .deposit(unit.getDeposit())
                    .rentfee(unit.getRentfee())
                    .managementfees(unit.getManagementfees())
                    .paymentday(unit.getPaymentday())
                    .memo(unit.getMemo())
                    .build()
            ).collect(Collectors.toList());
            groupDTO.setUnitDTOList(unitDTOList);
            groupDTO.setTotalDeposit(unitList.stream().mapToLong(Unit::getDeposit).sum());
            groupDTO.setTotalRentfee(unitList.stream().mapToLong(Unit::getRentfee).sum());
            groupDTO.setTotalManagementfees(unitList.stream().mapToLong(Unit::getManagementfees).sum());
            groupDTO.setTotalTenantCnt(10L);
        }

        return groupDTO;
    }
}
