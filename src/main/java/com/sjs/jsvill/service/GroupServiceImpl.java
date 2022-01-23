package com.sjs.jsvill.service;

import com.sjs.jsvill.dto.GroupDTO;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Unit;
import com.sjs.jsvill.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입 -> repository가 자동 주입
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository; //반드시 final로 선언

    @Override
    public Long register(GroupDTO dto) {
        log.info("DTO-------------");
        log.info(dto);

        Group group = dtoToEntity(dto);
        log.info(group);

        groupRepository.save(group);
        return group.getGroup_rowid();
    }

    @Override
    public List<GroupDTO> getList(Long member_rowid) {
        List<Group> groupList = groupRepository.getGroupWithMember(1L);
//        Map<Long, List<Unit>> groupMap = new HashMap();
        List<GroupDTO> groupDTOList = new ArrayList<>();

        for (Group group : groupList) {
//            System.out.println("group : " + group);
            List<Unit> unitList = groupRepository.getGroupWithUnit(group.getGroup_rowid());
//            for (Unit unit : unitList) {
//                System.out.println("unit : " + unit);
//            }
//            groupMap.put(group.getGroup_rowid(), unitList);
//            System.out.println(groupMap.get(group.getGroup_rowid()));

            groupDTOList.add(entitiesToDTO(group, unitList));
        }
        return groupDTOList;
    }

//    @Override
//    public PageResultDTO<GroupDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
//        log.info(pageRequestDTO);
//
//        Function<Object[], GroupDTO> fn = (en -> entityToDTO((Group)en[0]));
//
//        Page<Object[]> result = repository.getGroupWithAll(pageRequestDTO.getPageable(Sort.by("group_rowid").descending()));
//        
//        return
//    }
}
