package com.sjs.jsvill.service.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProdNotiService {
    private final KafkaTemplate<String, NotiMessage> kafkaTemplate;

    @Autowired
    public KafkaProdNotiService(KafkaTemplate<String, NotiMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void commentNotificationCreate(String userId, String message) {
        log.info("commentNotificationCreate-1");
        NotiMessage notiMessage = new NotiMessage(userId , message);
        log.info("리뷰 답글 알림 전송. userId : {}, message : {}",userId, message);
        kafkaTemplate.send("comment-notifications", notiMessage);
    }

    public void sendMessageWithKey(String topic, String key, String message) {
        System.out.println(String.format("Produce message with key to topic %s: key=%s, message=%s", topic, key, message));
//        this.kafkaTemplate.send(topic, key, message);
    }
}
