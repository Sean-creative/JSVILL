package com.sjs.jsvill.util;


import com.sjs.jsvill.service.sms.NaverSmsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

@SpringBootTest
public class SendSmsTest {

    @Autowired
    NaverSmsService naverSmsService;

    @Test
    public void sendSms() {
        String code;
        try {
            code = naverSmsService.sendRandomMessage("01050070615");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("code : " + code);
    }
}
