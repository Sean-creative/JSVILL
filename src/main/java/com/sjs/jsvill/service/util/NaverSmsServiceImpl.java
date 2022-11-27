package com.sjs.jsvill.service.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service //스프링에서 빈으로 처리되도록
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입
public class NaverSmsServiceImpl implements SmsService {

    @Override
    public String sendRandomMessage(String tel) throws UnsupportedEncodingException {
        Naver_Sens_V2 message = new Naver_Sens_V2();
        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 6; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }
        System.out.println("회원가입 문자 인증 => " + numStr);
        message.send_msg(tel, numStr);

        return numStr;
    }
}
