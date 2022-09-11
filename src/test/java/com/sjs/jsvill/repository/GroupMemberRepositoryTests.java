package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.GroupMember;
import com.sjs.jsvill.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

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
}
