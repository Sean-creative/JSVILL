package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity._GroupType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
                    .groupType(_GroupType.builder()._grouptype_rowid(1L)
                    .title("zz").build())
                    .title("Test-title...." + i)
                    .addr1("Test-addr1...." + i)
                    .postnum("Test-postnum...." + i)
                    .memo("Test-memo...." + i)
                    .build();
            System.out.println(groupRepository.save(group));
        });
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
