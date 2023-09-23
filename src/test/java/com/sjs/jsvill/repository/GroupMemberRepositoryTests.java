package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.GroupMember;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class GroupMemberRepositoryTests {

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Commit
    @Transactional
    @Test
    public void deleteByGroup() {
        System.out.println(groupMemberRepository.deleteByGroup(4L));
    }

    @Test
    public void save() {
        Group group = Group.builder().group_rowid(1L).build();
        Member member = Member.builder().memberRowid(1L).build();
        GroupMember groupMember = GroupMember.builder().member(member).group(group).build();
        groupMemberRepository.save(groupMember);
    }

    @Test
    public void findGroupMemberByGroup() {
        List<GroupMember> list = groupMemberRepository.findGroupMembersByMember_MemberRowid(1L);

        System.out.println("list.size: " + list.size());
        System.out.println("list: " + list);
        list.forEach(i -> {Json.stringToJson(i, "findGroupMemberByGroup Test");}
        );
    }
}
