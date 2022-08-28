package com.sjs.jsvill.service.sean.group;

import com.sjs.jsvill.dto.sean.GroupDTO;
import com.sjs.jsvill.dto.sean.UnitDTO;
import com.sjs.jsvill.entity.sean.Group;
import com.sjs.jsvill.entity.sean.Unit;
import com.sjs.jsvill.entity.sub._GroupType;

import java.util.List;
import java.util.stream.Collectors;

public interface GroupService {
    Long register(GroupDTO dto);
    List<GroupDTO> getList(Long member_rowid);
    Long remove(Long group_rowid);
    Group get(Long group_rowid);
    void modify(GroupDTO groupDTO);

    //파라미터로 받는건 DTO인데 -> DB에 접근하는 데이터는 엔티티로 바꿔줘야함
    default Group dtoToEntity(GroupDTO dto) {
        _GroupType groupType = _GroupType.builder()._grouptype_rowid(dto.getGroupTypeRowid()).build();

        Group group = Group.builder()
                .group_rowid(dto.getGroupRowid())
                ._grouptype(groupType)
                .title(dto.getTitle())
                .landaddr(dto.getLandAddr())
                .roadaddr(dto.getRoadAddr())
                .lat(dto.getLat())
                .lng(dto.getLng())
                .postNum(dto.getPostNum())
                .memo(dto.getMemo())
                .completiondate(dto.getCompletionDate())
                .build();
        return group;
    }

    default GroupDTO entityToDTO(Group group) {
        GroupDTO groupDTO = GroupDTO.builder()
                .groupRowid(group.getGroup_rowid())
                .groupTypeRowid(group.get_grouptype().get_grouptype_rowid())
                .title(group.getTitle())
                .landAddr(group.getLandaddr())
                .roadAddr(group.getRoadaddr())
                .lat(group.getLat())
                .lng(group.getLng())
                .postNum(group.getPostNum())
                .memo(group.getMemo())
                .build();
        return groupDTO;
    }

    //unit이 여러개일 때
    default GroupDTO entitiesToDTO(Group group, List<Unit> unitList) {
        GroupDTO groupDTO = GroupDTO.builder()
                .groupRowid(group.getGroup_rowid())
                .groupTypeRowid(group.get_grouptype().get_grouptype_rowid())
                .title(group.getTitle())
                .landAddr(group.getLandaddr())
                .roadAddr(group.getRoadaddr())
                .lat(group.getLat())
                .lng(group.getLng())
                .postNum(group.getPostNum())
                .memo(group.getMemo())
                .completionDate(group.getCompletiondate())
                .build();

        if(unitList.get(0) != null) {
            List<UnitDTO> unitDTOList = unitList.stream().map(unit -> UnitDTO.builder()
                    .unitRowid(unit.getUnit_rowid())
                    .groupRowid(unit.getGroup().getGroup_rowid())
                    .detailAddr(unit.getDetailaddr())
                    .memo(unit.getMemo())
                    .build()
            ).collect(Collectors.toList());
            groupDTO.setUnitDTOList(unitDTOList);
//            groupDTO.setTotalDeposit(unitList.stream().mapToLong(Unit::getDeposit).sum());
//            groupDTO.setTotalRentFee(unitList.stream().mapToLong(Unit::getRentfee).sum());
//            groupDTO.setTotalManagementFees(unitList.stream().mapToLong(Unit::getManagementfees).sum());
            //TODO 계약쪽 해결되면 고칠 것!
            groupDTO.setTotalTenantCnt(0L);
        }

        return groupDTO;
    }
}
