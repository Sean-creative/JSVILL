package com.sjs.jsvill.service.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service //스프링에서 빈으로 처리되도록
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입
public class SmsServiceImpl implements SmsService {

    @Override
    public void sendSms() {
        String api_key = "NCS1AL38ZNBOYEX1";
        String api_secret = "26CX0JJLVLOLNMY9DVAM35FE4BZ3LJ2Y";
        Message coolsms = new Message(api_key, api_secret);
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("to", "010-5007-0615"); //등록한 발신자 번호
        params.put("from", "010-5007-0615"); //수신자 번호
        params.put("type", "SMS");
        params.put("text", "JSVILL SMS TEST"); //문자 내용
        params.put("app_version", "test app 1.2");

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
