package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Calendar;
import com.sjs.jsvill.entity.Group;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.stream.LongStream;

import static org.mockito.BDDMockito.given;

@SpringBootTest
public class CalendarRepositoryTests {

    @Autowired
    private CalendarRepository calendarRepository;

    @Test
    public void register() {
        Group group = Group.builder().group_rowid(3L).build();

        LongStream.rangeClosed(1, 5).forEach(i -> {
            Calendar calendar = Calendar.builder()
                    .group(group)
                    .title("일정"+i)
                    .description("설명"+i)
                    .start("2023-04-1"+i+"T12:30")
                    .end("2023-04-1"+i+"T13:30")
                    .backgroundcolor("#9775fa")
                    .textcolor("#ffffff")
                    .isallday(false)
                    .build();
            System.out.println(calendarRepository.save(calendar));
        });
    }



    @Test
    @Commit
    public void delete() {
        calendarRepository.deleteByBundleId(23087783L);
    }
}
