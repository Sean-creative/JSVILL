package com.sjs.jsvill.service.kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsNotiService {
//    @KafkaListener(topics = {"noti"}, groupId = "foo")
//    public void consume(String message) throws IOException {
//        System.out.println(String.format("Consumed message : %s", message));
//        // 인위적으로 폴링 간격을 늘림
//        try {
//            Thread.sleep(10000); // 10초 지연
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}