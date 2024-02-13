package com.sjs.jsvill.service.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@AllArgsConstructor
@Builder
public class Notifications {
    private String userPhone;
    private String message;
    private String type;
}