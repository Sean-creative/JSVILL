package com.sjs.jsvill.service.group;

import com.sjs.jsvill.dto.GroupDTO;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.GroupMember;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity.Unit;
import com.sjs.jsvill.repository.GroupMemberRepository;
import com.sjs.jsvill.repository.GroupRepository;
import com.sjs.jsvill.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입 -> repository가 자동 주입
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository; //반드시 final로 선언
    private final GroupMemberRepository groupMemberRepository;
    private final UnitRepository unitRepository;

    @Override
    @Transactional
    public Long register(GroupDTO groupDTO) {
        log.info("groupDTO : " + groupDTO);

        Group group = dtoToEntity(groupDTO);
        log.info("group : " + group);

        Group temp = groupRepository.save(group);
        log.info("temp : " + temp);
        //현재 로그인된 멤버의 rowid는 어디서 가져오나?? -> 일단은 트랜잭션걸기 위해서 이 함수안에서 처리하기로함
        Member member = Member.builder().member_rowid(1L).build();
        GroupMember groupMember = GroupMember.builder().member(member).group(temp).build();
        groupMemberRepository.save(groupMember);
        return group.getGroup_rowid();
    }

    @Override
    public List<GroupDTO> getList(Long member_rowid) {
        List<Group> groupList = groupRepository.getGroupWithAll(member_rowid);
        List<GroupDTO> groupDTOList = new ArrayList<>();

        for (Group group : groupList) {
//            System.out.println("group : " + group);
            List<Unit> unitList = groupRepository.getUnitWithGroup(group.getGroup_rowid());
//            for (Unit unit : unitList) {
//                System.out.println("unit : " + unit);
//            }
//                System.out.println("unitList : " + unitList);
            groupDTOList.add(entitiesToDTO(group, unitList));
        }
        return groupDTOList;
    }

    @Override
    @Transactional
    public Long remove(Long group_rowid) {
        //삭제해야할 것들 -> groupMember, group, unit
        //TODO 그룹이 삭제되면 호실들에게 속한 계약도 삭제되어야 한다.
        groupMemberRepository.deleteByGroup(group_rowid);
        groupRepository.deleteById(group_rowid);
        unitRepository.deleteByGroupRowid(group_rowid);
        return group_rowid;
    }

    @Override
    public Group get(Long group_rowid) {
        Optional<Group> group = groupRepository.findById(group_rowid);
        return group.get();
    }

    @Transactional
    @Override
    public void modify(GroupDTO groupDTO) {
        Group group = groupRepository.getById(groupDTO.getGroupRowid());

        if(group != null) {
            group.changeTitle(groupDTO.getTitle());
            group.changeAddr1(groupDTO.getAddr1());
            group.changePostNum(groupDTO.getPostNum());
            group.changeMemo(groupDTO.getMemo());
            group.changeCompletionDate(groupDTO.getCompletionDate());
            groupRepository.save(group);
        }
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
