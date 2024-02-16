package com.sjs.jsvill.service.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Producer의 NotificationMessage 클래스의 패키지 위치와 동일해야 한다??
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotiMessage {
    private Long memberRowid;
    private String userPhone; //emitter에서 rowid는 사용할 수 없어서 핸드폰으로 사용
    private String contents;
    private Integer daysAgo;
}