package com.sjs.jsvill.service.group;

import com.sjs.jsvill.dto.GroupDTO;
import com.sjs.jsvill.dto.PageRequestDTO;
import com.sjs.jsvill.dto.PageResultDTO;
import com.sjs.jsvill.entity.Group;

public interface GroupService {
    Long register(GroupDTO dto);

//    PageResultDTO<GroupDTO, Object[]>getList(PageRequestDTO pageRequestDTO); //목록처리

    //파라미터로 받는건 DTO인데 -> DB에 접근하는 데이터는 엔티티로 바꿔줘야함
    default Group dtoToEntity(GroupDTO dto) {
        Group group = Group.builder()
                .group_rowid(dto.getGroup_rowid())
                .member(dto.getMember())
                .groupType(dto.getGroupType())
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
                .member(group.getMember())
                .groupType(group.getGroupType())
                .title(group.getTitle())
                .addr1(group.getAddr1())
                .postnum(group.getPostnum())
                .memo(group.getMemo())
                .build();
        return groupDTO;
    }
}
