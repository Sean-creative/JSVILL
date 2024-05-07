package com.sjs.jsvill.controller.kafka;

import com.sjs.jsvill.dto.member.MemberDTO;
import com.sjs.jsvill.service.kafka.ConsReminderService;
import com.sjs.jsvill.service.kafka.ProdReminderService;
import com.sjs.jsvill.service.kafka.ReminderMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequestMapping(value = "/kafka")
@Slf4j
@RequiredArgsConstructor
public class ReminderApiController {

    private final ConsReminderService consReminderService;
    private final ProdReminderService producer;
    public static final Long DEFAULT_TIMEOUT = 1800L * 1000;


    @GetMapping(value = "/api/sse-connection", produces = "text/event-stream")
    public SseEmitter stream(@AuthenticationPrincipal MemberDTO memberDTO, @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) throws IOException {
        return consReminderService.addEmitter(String.valueOf(memberDTO.getPhoneNumber()), lastEventId);
    }

    @PostMapping("/api/v1/reply/reminders")
    public ResponseEntity<?> sendReplyReminders(@RequestBody ReminderMessage reminderMessage) {
        this.producer.sendToProducer(reminderMessage, false);
        return ResponseEntity.ok().build();
    }
}