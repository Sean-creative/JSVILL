package com.sjs.jsvill.controller.util;

import com.sjs.jsvill.dto.member.MemberDTO;
import com.sjs.jsvill.dto.sms.SMSDTOReq;
import com.sjs.jsvill.service.sms.NaverSmsService;
import com.sjs.jsvill.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/sms")
@Log4j2
@RequiredArgsConstructor
public class SMSController {
    private final NaverSmsService naverSmsService;

    @ResponseBody
    @PostMapping("/send")
    public void send(@RequestBody SMSDTOReq smsDtoReq, @AuthenticationPrincipal MemberDTO memberDTO) {
        System.out.println("sms-send 되는중??");

        Json.stringToJson(memberDTO, "SMSController-send/Post/send");

        
        try {
            naverSmsService.sendNormalMessage(smsDtoReq);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}

