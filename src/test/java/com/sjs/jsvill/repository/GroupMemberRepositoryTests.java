package com.sjs.jsvill.repository;

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
}
