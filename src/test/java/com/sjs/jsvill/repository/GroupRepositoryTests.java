package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.GroupMember;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity._GroupType;
import com.sjs.jsvill.repository.group.GroupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootTest
public class GroupRepositoryTests {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            Group group = Group.builder()
                    .group_rowid(Integer.toUnsignedLong(i))
                    .groupMember(GroupMember.builder().groupmember_rowid(1L).build())
                    .groupType(_GroupType.builder()._grouptype_rowid(10L).build())
                    .title("Test-title...." + i)
                    .addr1("Test-addr1...." + i)
                    .postnum("Test-p" + i)
                    .memo("Test-memo...." + i)
                    .build();
            System.out.println(groupRepository.save(group));
        });
    }

    @Test
    public void testGroupWithAll() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("group_rowid").descending());
        Member member = Member.builder().member_rowid(1L).build();
        Page<Object[]> result = groupRepository.getGroupWithAll(pageRequest, 1L);
//        System.out.println(result.toString());

        for (Object[] arr : result) {
            System.out.println("---------");
            System.out.println(Arrays.toString(arr));
        }
    }

//    @Test
//    public void updateTest() {
//        Optional<Guestbook> result = groupRepository.findById(300L);
//
//        //존재하는 번호로 테스트
//        if(result.isPresent()){
//            Guestbook guestbook = result.get();
//            guestbook.changeTitle("Changed Title....");
//            guestbook.changeContent("Changed Content...");
//            groupRepository.save(guestbook);
//        }
//    }
//
//    @Test
//    public void testQuery1() {
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
//        QGuestbook qGuestbook = QGuestbook.guestbook; //1
//        String keyword = "1";
//        BooleanBuilder builder = new BooleanBuilder(); //2
//        BooleanExpression expression = qGuestbook.title.contains(keyword); //3
//        builder.and(expression); //4
//        Page<Guestbook> result = groupRepository.findAll(builder, pageable); //5
//
//        result.stream().forEach(guestbook -> {
//            System.out.println(guestbook);
//        });
//    }
//
//    @Test
//    public void testQuery2() {
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
//        QGuestbook qGuestbook = QGuestbook.guestbook;
//        String keyword = "1";
//        BooleanBuilder builder = new BooleanBuilder();
//        BooleanExpression exTitle = qGuestbook.title.contains(keyword);
//        BooleanExpression exContent = qGuestbook.content.contains(keyword);
//        BooleanExpression exAll = exTitle.or(exContent); // 1---------------
//        builder.and(exAll); //2----------
//        builder.and(qGuestbook.gno.gt(0L)); //3-------------------
//        Page<Guestbook> result = groupRepository.findAll(builder, pageable);
//
//        result.stream().forEach(guestbook -> {
//            System.out.println(guestbook);
//        });
//    }
}
