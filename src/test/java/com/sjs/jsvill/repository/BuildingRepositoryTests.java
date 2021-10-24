package com.sjs.jsvill.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.sjs.jsvill.entity.Building;
import com.sjs.jsvill.entity.sample.Guestbook;
import com.sjs.jsvill.entity.sample.QGuestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BuildingRepositoryTests {

    @Autowired
    private BuildingRepository buildingRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            Building building = Building.builder()
                    .member_rowid(Integer.toUnsignedLong(i))
                    ._buildingtype_rowid(i)
                    .title("Test1...." + i)
                    .addr1("Test1...." + i)
                    .addr2("Test1...." + i)
                    .postnum("Test1...." + i)
                    .memo("Test1...." + i)
                    .tenantcnt(i)
                    .deposit(Integer.toUnsignedLong(i))
                    .monthly(Integer.toUnsignedLong(i))
                    .managementfees(Integer.toUnsignedLong(i))
                    .build();
            System.out.println(buildingRepository.save(building));
        });
    }

//    @Test
//    public void updateTest() {
//        Optional<Guestbook> result = buildingRepository.findById(300L);
//
//        //존재하는 번호로 테스트
//        if(result.isPresent()){
//            Guestbook guestbook = result.get();
//            guestbook.changeTitle("Changed Title....");
//            guestbook.changeContent("Changed Content...");
//            buildingRepository.save(guestbook);
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
//        Page<Guestbook> result = buildingRepository.findAll(builder, pageable); //5
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
//        Page<Guestbook> result = buildingRepository.findAll(builder, pageable);
//
//        result.stream().forEach(guestbook -> {
//            System.out.println(guestbook);
//        });
//    }
}
