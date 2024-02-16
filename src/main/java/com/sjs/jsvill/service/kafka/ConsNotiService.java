package com.sjs.jsvill.service.kafka;


import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity.Reminder;
import com.sjs.jsvill.repository.EmitterRepository;
import com.sjs.jsvill.service.reminder.member.ReminderService;
import com.sjs.jsvill.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

import static com.sjs.jsvill.controller.kafka.NotificationApiController.DEFAULT_TIMEOUT;


@Service
@Slf4j
@RequiredArgsConstructor
public class ConsNotiService {
    private final ReminderService reminderService;
    private final EmitterRepository emitterRepository;

    private static final int MAX_NOTIFICATIONS_COUNT = 6;

    @KafkaListener(topics = "calendar-noti-schedule", groupId = "group_1")
    public void listenForSchedule(NotiMessage notiMessage) {
        //인위적으로 폴링 간격을 늘림
        log.info("인위적으로 폴링 간격을 늘림 -- 5초 지연");
        try {
            Thread.sleep(5000); // 10초 지연
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Json.stringToJson(notiMessage, "KafkaListener-listenForSchedule");

        //스케줄로 메세지를 컨슘하려면 -> 리마인더에 넣어야함
        Reminder reminder = Reminder.builder()
                .member(Member.builder().memberRowid(notiMessage.getMemberRowid()).build())
                .contents(notiMessage.getContents())
                .daysAgo(notiMessage.getDaysAgo())
                .build();
        Json.stringToJson(reminderService.createReminder(reminder), "listenForSchedule");
    }

    //Emitter는 데이터 스트림을 클라이언트로 보내는 역할을 하는 객체이다.
    //SSE 연결된 클라이언트에게 실시간으로 알림을 전달한다.
    @KafkaListener(topics = "calendar-noti-emitter", groupId = "group_1")
    public void listenForEmitter(NotiMessage notiMessage) {
        //인위적으로 폴링 간격을 늘림
        log.info("인위적으로 폴링 간격을 늘림 -- 5초 지연");
        try {
            Thread.sleep(5000); // 10초 지연
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Json.stringToJson(notiMessage, "KafkaListener-listenForEmitter");


        //1. 리마인더에 등록도 해줘야한다.
        Reminder reminder = Reminder.builder()
            .member(Member.builder().memberRowid(notiMessage.getMemberRowid()).build())
            .contents(notiMessage.getContents())
            .daysAgo(notiMessage.getDaysAgo())
            .build();
        Json.stringToJson(reminderService.createReminder(reminder), "listenForEmitter");


        //2.비동기적으로 emiiter에도 전해줘야하고

// 알림이 어차피 스케줄링 작업에서 하루단위로 초기화 되기 때문에 일단은 따로 지울 필요는 없을듯.
//        notificationsService.insertNotifications(notifications);
//        int curCnt = notificationsService.countNotificationsByUserId(Long.valueOf(userPhone));
//        if (curCnt > MAX_NOTIFICATIONS_COUNT) {
//            int delCount = curCnt - MAX_NOTIFICATIONS_COUNT;
//            notificationsService.deleteOldestNotificationsByUserId(Long.valueOf(userPhone), delCount);
//        }

        Map<String, SseEmitter> sseEmitters = emitterRepository.findAllEmitterStartWithById(notiMessage.getUserPhone());
        log.info("sseEmitters.size() : " + sseEmitters.size());
        sseEmitters.forEach(
                (key, emitter) -> {
                    emitterRepository.saveEventCache(key, notiMessage);
                    sendToClient(emitter, key, notiMessage);
                }
        );
    }

    //'Emitter'는 이러한 데이터 스트림을 클라이언트로 보내는 역할을 하는 객체입니다.
    // SseEmitter 클래스는 Spring Framework에서 이 기능을 구현하기 위해 제공되는 클래스로, 하나의 클라이언트에 대한 연결을 관리합니다.
    // 이 객체를 통해 서버는 클라이언트에게 데이터를 보낼 수 있으며, 연결이 완료되거나 타임아웃이 발생했을 때 적절한 처리를 할 수 있습니다.
    //SSE 연결된 사용자 정보를 받아 메시지를 전송
    private void sendToClient(SseEmitter emitter, String emitterId, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(emitterId)
                    .data(data));
            log.info("Kafka로 부터 전달 받은 메세지 전송. emitterId : {}, message : {}", emitterId, data);
        } catch (IOException e) {
            emitterRepository.deleteById(emitterId);
            log.error("메시지 전송 에러 : {}", e);
        }
    }

    //브라우저에 대한 연결
    public SseEmitter addEmitter(String userPhone, String lastEventId) {
        log.info("addEmitter-1 userPhone : {}, lastEventId : {}", userPhone, lastEventId);
        String emitterId = userPhone + "_" + System.currentTimeMillis();
        SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));
        log.info("emitterId : {} 사용자 emitter 연결 ", emitterId);

        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            emitterRepository.deleteById(emitterId);
        });
        emitter.onTimeout(() -> {
            log.info("onTimeout callback");
            emitterRepository.deleteById(emitterId);
        });

        sendToClient(emitter, emitterId, "connected!"); // 503 에러방지 더미 데이터

        log.info("!lastEventId.isEmpty() : {}",!lastEventId.isEmpty());
        if (!lastEventId.isEmpty()) {
            //서버-클라이언트 간에 연결이 끊겼을 때, 클라이언트가 마지막으로 수신한 이벤트 ID를 서버에 알려주어 서버가 해당 지점부터 이벤트를 다시 전송할 수 있도록
            Map<String, Object> events = emitterRepository.findAllEventCacheStartWithById(userPhone);
            events.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> {
                        log.info("설마 여기가?");
                        sendToClient(emitter, entry.getKey(), entry.getValue());
                    });
        }
        return emitter;
    }

    @Scheduled(fixedRate = 180000) // 3분마다 heartbeat 메세지 전달.
    public void sendHeartbeat() {
        Map<String, SseEmitter> sseEmitters = emitterRepository.findAllEmitters();
        sseEmitters.forEach((key, emitter) -> {
            try {
                log.info("하트비트 메세지 전송 key : {}, emitter : {}", key, emitter);
                emitter.send(SseEmitter.event().id(key).name("heartbeat").data(""));
            } catch (IOException e) {
                emitterRepository.deleteById(key);
                log.error("하트비트 전송 실패: {}", e.getMessage());
            }
        });
    }
}