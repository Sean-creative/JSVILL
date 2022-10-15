package com.sjs.jsvill.util;


import com.sjs.jsvill.service.util.SmsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

@SpringBootTest
public class SendSmsTest {

    @Autowired
    SmsService smsService;

    @Test
    public void sendSms() {

//        String str = "ㅎㅎ";
//        try {
//            System.out.println("test : " + new String(str.getBytes("UTF-8"), "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }


        String code = null;
        try {
            code = smsService.sendRandomMessage("01050070615");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("code : " + code);
//        session.setAttribute("rand", code);
    }
}
