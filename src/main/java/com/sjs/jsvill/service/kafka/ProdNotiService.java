package com.sjs.jsvill.service.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProdNotiService {
    private final KafkaTemplate<String, NotiMessage> kafkaTemplate;

    @Autowired
    public ProdNotiService(KafkaTemplate<String, NotiMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendToProducer(String userId, String message) {
        log.info("sendToProducer-1");
        NotiMessage notiMessage = new NotiMessage(1L, userId , message, "5");
        kafkaTemplate.send("calendar-noti", notiMessage);
    }

    public void sendMessageWithKey(String topic, String key, String message) {
        System.out.println(String.format("Produce message with key to topic %s: key=%s, message=%s", topic, key, message));
//        this.kafkaTemplate.send(topic, key, message);
    }
}
