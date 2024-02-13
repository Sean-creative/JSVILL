package com.sjs.jsvill.service.kafka;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerServiceT {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerServiceT(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageWithKey(String topic, String key, String message) {
        System.out.println(String.format("Produce message with key to topic %s: key=%s, message=%s", topic, key, message));
        this.kafkaTemplate.send(topic, key, message);
    }

    public void sendMessageToSpecificPartition(String topic, Integer partition, String message) {
        System.out.println(String.format("Produce message to topic %s, partition %d: %s", topic, partition, message));
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, partition, null, message);
        this.kafkaTemplate.send(record);
    }
}
