package com.sjs.jsvill.entity.defaultType;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name="_livingtype")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class _LivingType {

    @Id
    private Long _livingtype_rowid;

    @Column(length = 64, nullable = false)
    private String title;
    //    10	거주 X
    //    20	거주 O
    //    30	거주 예정

    //contractTenantLogRepository에 등록할 때, 어떤 상태로 남기는 로그인지 판별
    public static _LivingType isStartedContract(String startdate) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatedNow = now.format(formatter);

        Date format1;
        Date format2;
        try {
            format1 = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
            format2 = new SimpleDateFormat("yyyy-MM-dd").parse(formatedNow);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        long diffSec = (format1.getTime() - format2.getTime());
        System.out.println("diffSec : " + diffSec);

        if(diffSec<=0) return _LivingType.builder()._livingtype_rowid(20L).build();
        else return _LivingType.builder()._livingtype_rowid(30L).build();
    }
}