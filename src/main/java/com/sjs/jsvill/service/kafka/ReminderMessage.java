package com.sjs.jsvill.service.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReminderMessage {
    private Long memberRowid;
    private String userPhone; //emitter에서 rowid는 사용할 수 없어서 핸드폰으로 사용
    private String contents;
    private Integer daysAgo;
}