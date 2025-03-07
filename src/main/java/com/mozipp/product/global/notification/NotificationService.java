package com.mozipp.product.global.notification;

import com.mozipp.product.domain.product.service.UserFindService;
import com.mozipp.product.global.config.redis.RedisListenerConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class NotificationService {

    // 사용자 ID를 키로 하는 SseEmitter 맵
    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public SseEmitter createEmitter(Long userId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.put(userId, emitter);

        emitter.onCompletion(() -> emitters.remove(userId));
        emitter.onTimeout(() -> {
            emitter.complete();
            emitters.remove(userId);
        });
        emitter.onError((e) -> {
            emitters.remove(userId);
            emitter.completeWithError(e);
        });

        try {
            emitter.send(SseEmitter.event().name("connected").data("Connection established"));
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }

    public void sendNotification(Long userId, String eventName, String message) {
        SseEmitter emitter = emitters.get(userId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .name(eventName)
                        .data(message));
            } catch (IOException e) {
                emitters.remove(userId);
                emitter.completeWithError(e);
                logger.info("Failed to send notification to userId: " + userId + ", error: " + e.getMessage());
            }
        } else {
            logger.info("No active emitter found for userId: " + userId);
        }
    }
}