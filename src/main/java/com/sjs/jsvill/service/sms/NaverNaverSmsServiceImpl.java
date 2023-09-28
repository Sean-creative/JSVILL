package com.sjs.jsvill.service.sms;

import com.sjs.jsvill.dto.sms.SMSDTOReq;
import com.sjs.jsvill.util.Naver_Sens_V2;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service //스프링에서 빈으로 처리되도록
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입
public class NaverNaverSmsServiceImpl implements NaverSmsService {

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
        message.send_msg(tel, "[JSVILL] 본인확인을 위해 인증번호 ["+numStr+"]를 입력해 주세요.");
        return numStr;
    }

    @Override
    public String sendNormalMessage(SMSDTOReq smsDtoReq) throws UnsupportedEncodingException {
        Naver_Sens_V2 message = new Naver_Sens_V2();
        message.send_msg(smsDtoReq.getToTel().replace("-", ""), "[JSVILL] "+smsDtoReq.getContent()+"\n from : "+smsDtoReq.getName()+", "+smsDtoReq.getFromTel());
        return "";
    }
}
