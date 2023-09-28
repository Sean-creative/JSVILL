package com.sjs.jsvill.service.group;

import com.sjs.jsvill.dto.GroupDTO;
import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Unit;
import com.sjs.jsvill.entity.defaultType._GroupType;

import java.util.List;
import java.util.stream.Collectors;

public interface GroupService {
    Long register(GroupDTO dto, Long memberRowid);
    List<GroupDTO> getList(Long memberRowid);
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
    default GroupDTO entitiesToDTO(Group group, List<Unit> unitList, List<Contract> contractList, Long totalTenantCnt) {
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

        if(!unitList.isEmpty()) {
            List<UnitDTO> unitDTOList = unitList.stream().map(unit -> UnitDTO.builder()
                    .unitRowid(unit.getUnit_rowid())
                    .groupRowid(unit.getGroup().getGroup_rowid())
                    .detailAddr(unit.getDetailaddr())
                    .memo(unit.getMemo())
                    .build()
            ).collect(Collectors.toList());
            groupDTO.setUnitDTOList(unitDTOList);

            groupDTO.setTotalDeposit(contractList.stream().mapToLong(Contract::getDeposit).sum());
            groupDTO.setTotalRentFee(contractList.stream().mapToLong(Contract::getRentfee).sum());
            groupDTO.setTotalManagementFees(contractList.stream().mapToLong(Contract::getManagementfees).sum());
            groupDTO.setTotalTenantCnt(totalTenantCnt);
        }

        return groupDTO;
    }
}
