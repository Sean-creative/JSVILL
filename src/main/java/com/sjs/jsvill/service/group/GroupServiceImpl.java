package com.sjs.jsvill.service.group;

import com.sjs.jsvill.dto.GroupDTO;
import com.sjs.jsvill.entity.*;
import com.sjs.jsvill.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입 -> repository가 자동 주입
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository; //반드시 final로 선언
    private final GroupMemberRepository groupMemberRepository;
    private final UnitRepository unitRepository;
    private final ContractRepository contractRepository;
    private final ContractTenantRepository contractTenantRepository;

    @Override
    @Transactional
    public Long register(GroupDTO groupDTO, Long memberRowid) {
        Group group = dtoToEntity(groupDTO);
        Group temp = groupRepository.save(group);
        Member member = Member.builder().memberRowid(memberRowid).build();
        GroupMember groupMember = GroupMember.builder().member(member).group(temp).build();
        groupMemberRepository.save(groupMember);
        return group.getGroup_rowid();
    }

    @Override
    public List<GroupDTO> getList(Long memberRowid) {
        List<GroupDTO> groupDTOList = new ArrayList<>();
        List<Group> groupList = groupRepository.getGroupByMember(memberRowid);
        for (Group group : groupList) {
            List<Unit> unitList = groupRepository.getUnitByGroup(group.getGroup_rowid());
            List<Contract> progressContractList = new ArrayList<>();
            Long totalTenantCnt = 0L;

            System.out.println("unitList.size() : " + unitList.size());
            unitList.forEach(i -> {
                System.out.println("i.getUnit_rowid() : " + i.getUnit_rowid());
            });
            if(!unitList.isEmpty()){
                //호실의 개수만큼 DB를 조회하는것이 부담스러움 -> 조회해야하는 호실 rowid를 한번에 던져서 현재 진행중인 계약의 리스트를 가져옴
                List<Long> unitRowidList = unitList.stream().map(Unit::getUnit_rowid).toList();
                progressContractList = contractRepository.findProgressContractsByUnits(unitRowidList);

                List<Long> contractRowidList = progressContractList.stream().map(Contract::getContract_rowid).toList();
                totalTenantCnt = contractTenantRepository.findProgressContractTenantsByContract(contractRowidList);
            }
            groupDTOList.add(entitiesToDTO(group, unitList, progressContractList, totalTenantCnt));
        }
        return groupDTOList;
    }

    @Override
    @Transactional
    public Long remove(Long group_rowid) {
        groupRepository.deleteById(group_rowid);
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
//        Group group = groupRepository.getById(groupDTO.getGroupRowid());

        Group group = GroupDTO.dtoToEntity(groupDTO);
        groupRepository.save(group);
    }
}
