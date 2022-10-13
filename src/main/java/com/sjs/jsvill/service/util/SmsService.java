package com.sjs.jsvill.service.util;

import java.io.UnsupportedEncodingException;

public interface SmsService {
    String sendRandomMessage(String tel) throws UnsupportedEncodingException;
}
