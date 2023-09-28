package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.defaultType._GroupType;
import com.sjs.jsvill.repository.sub._GroupTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class _GroupTypeRepositoryTests {

    @Autowired
    private _GroupTypeRepository groupTypeRepository;

    @Test
    public void testRegister() {
        System.out.println("groupTypeRepository : " + groupTypeRepository.toString());
       _GroupType groupType = _GroupType.builder()._grouptype_rowid(10L).title("villa").build();
        _GroupType groupType2 = _GroupType.builder()._grouptype_rowid(20L).title("apartment").build();
        System.out.println(groupTypeRepository.save(groupType));
        System.out.println(groupTypeRepository.save(groupType2));
    }

//    @Test
//    public void testGroupWithAll() {
//        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("group_rowid").descending());
//        Member member = Member.builder().memberRowid(1L).build();
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
