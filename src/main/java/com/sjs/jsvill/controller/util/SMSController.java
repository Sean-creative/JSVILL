package com.sjs.jsvill.controller.util;

import com.sjs.jsvill.service.util.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sms")
@Log4j2
@RequiredArgsConstructor
public class SMSController {

    private final SmsService smsService;

    @RequestMapping("/send")
    public String send() {
        log.info("================sms - send==================");
//        smsService.sendSms();
        return "list";
    }
}

