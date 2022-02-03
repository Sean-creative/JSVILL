package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GroupRepositoryTests {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Test
    public void testRegister() {
        _GroupType groupType = _GroupType.builder()._grouptype_rowid(10L).build();
        _GroupType groupType2 = _GroupType.builder()._grouptype_rowid(20L).build();
        Member member = Member.builder().member_rowid(1L).build();

        //그룹을 만들때 GroupMember도 같이 만들어야한다.
        IntStream.rangeClosed(1, 2).forEach(i -> {
            //_GroupType은 이미 등록이 되어있는 상태이고, rowid로 사용해야함
            Group group = Group.builder()
                    .groupType(groupType)
                    .title("Test-title...." + i)
                    .addr1("Test-addr1...." + i)
                    .postNum("T-p" + i)
                    .memo("Test-memo...." + i)
                    .build();
            System.out.println(groupRepository.save(group));

            GroupMember groupMember = GroupMember.builder()
                    .member(member)
                    .group(group)
                    .build();
            System.out.println(groupMemberRepository.save(groupMember));
        });
    }

    @Test
    public void getGroupWithUnit() {
        List<Unit> result = groupRepository.getGroupWithUnit(1L);
        System.out.println("result.length : " + result.size());
        for (Unit unit : result) {
            System.out.println("unit : " + unit);

        }
    }

    @Test
    public void getGroupWithUnitGroupBy() {
        Object result = groupRepository.getGroupWithUnitGroupBy(1L);
        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void getGroupWithMember() {
        List<Group> result = groupRepository.getGroupWithMember(1L);
        for (Group arr : result) {
            System.out.println("arr : " + arr);
        }
    }

    @Test
    public void read() {
        Optional<Group> group = groupRepository.findById(29L);
        System.out.println("group : " + group.get());
    }


//    @Test
//    public void testGroupWithAll() {
//        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("group_rowid").descending());
//        Member member = Member.builder().member_rowid(1L).build();
//        Page<Object[]> result = groupRepository.getGroupWithAll(pageRequest, 1L);
////        System.out.println(result.toString());
//
//        for (Object[] arr : result) {
//            System.out.println("---------");
//            System.out.println(Arrays.toString(arr));
//        }
//    }

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
