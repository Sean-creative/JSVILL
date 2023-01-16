package com.sjs.jsvill.dto.sms;

import lombok.Data;

@Data
public class SMSDTOReq {
    String fromTel = "";
    String toTel;
    String name;
    String content;
}
