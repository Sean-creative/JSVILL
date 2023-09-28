package com.sjs.jsvill.service.sms;

import com.sjs.jsvill.dto.sms.SMSDTOReq;

import java.io.UnsupportedEncodingException;

public interface NaverSmsService {
    String sendRandomMessage(String tel) throws UnsupportedEncodingException;
    String sendNormalMessage(SMSDTOReq smsDtoReq) throws UnsupportedEncodingException;
}
