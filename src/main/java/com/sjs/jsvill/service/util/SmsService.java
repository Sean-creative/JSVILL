package com.sjs.jsvill.service.util;

import com.sjs.jsvill.dto.sms.SMSDTOReq;

import java.io.UnsupportedEncodingException;

public interface SmsService {
    String sendRandomMessage(String tel) throws UnsupportedEncodingException;
    String sendNormalMessage(SMSDTOReq smsDtoReq) throws UnsupportedEncodingException;
}
