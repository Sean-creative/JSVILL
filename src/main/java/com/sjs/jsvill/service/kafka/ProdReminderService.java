package com.sjs.jsvill.service.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProdReminderService {
    private final KafkaTemplate<String, ReminderMessage> kafkaTemplate;

    @Autowired
    public ProdReminderService(KafkaTemplate<String, ReminderMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendToProducer(ReminderMessage reminderMessage, boolean isSchedule) {
        if(isSchedule) kafkaTemplate.send("calendar-reminder-schedule", reminderMessage);
        else kafkaTemplate.send("calendar-reminder-emitter", reminderMessage);
    }

    public void sendMessageWithKey(String topic, String key, String message) {
        System.out.println(String.format("Produce message with key to topic %s: key=%s, message=%s", topic, key, message));
//        this.kafkaTemplate.send(topic, key, message);
    }
}
