package com.sjs.jsvill.service;

import com.sjs.jsvill.dto.GroupDTO;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity._GroupType;

public interface GroupService {
    Long register(GroupDTO dto);

//    PageResultDTO<GroupDTO, Object[]>getList(PageRequestDTO pageRequestDTO); //목록처리

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
}
