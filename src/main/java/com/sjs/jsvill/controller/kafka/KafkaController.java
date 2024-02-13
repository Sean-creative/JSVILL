package com.sjs.jsvill.controller.kafka;

import com.sjs.jsvill.dto.NotiMessageDTO;
import com.sjs.jsvill.service.kafka.KafkaProdNotiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
@Slf4j
public class KafkaController {
    private final KafkaProdNotiService producer;

    @Autowired
    KafkaController(KafkaProdNotiService producer) {
        this.producer = producer;
    }

    @PostMapping
    public String sendMessage(String topic,String key, String message) {
        this.producer.sendMessageWithKey(topic, key, message);
        return "Send message to topic " + topic;
    }

    @PostMapping("/api/v1/reply/notifications")
    public ResponseEntity<?> sendReplyNotifications(@RequestBody NotiMessageDTO dto) {
//        Long userPhone = reviewService.selectUserIdByReviewId(dto.getReviewId());
        String userPhone = dto.getUserPhone();
        String message = userPhone + "의 일정이 "+dto.getMessage()+" 남았습니다.";

        log.info("sendReplyNotifications");
        //알림이 오면 카프카 producer로 처리
        this.producer.commentNotificationCreate(userPhone.toString(), message);
        return ResponseEntity.ok().build();
    }
}
