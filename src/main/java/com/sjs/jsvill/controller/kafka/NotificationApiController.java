package com.sjs.jsvill.controller.kafka;

import com.sjs.jsvill.dto.member.MemberDTO;
import com.sjs.jsvill.service.kafka.ConsNotiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class NotificationApiController {

    private final ConsNotiService emitterService;
    public static final Long DEFAULT_TIMEOUT = 1800L * 1000;

    @GetMapping(value = "/api/sse-connection", produces = "text/event-stream")
    public SseEmitter stream(@AuthenticationPrincipal MemberDTO memberDTO, @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) throws IOException {
        log.info("stream-1");
        return emitterService.addEmitter(String.valueOf(memberDTO.getPhoneNumber()), lastEventId);
    }
}