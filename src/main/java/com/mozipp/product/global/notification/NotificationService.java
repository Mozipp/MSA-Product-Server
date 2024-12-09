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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    // 사용자 ID를 키로 하는 SseEmitter 맵
    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final Executor executor = Executors.newCachedThreadPool(); // 스레드 풀

    public SseEmitter createEmitter(Long userId) {
        // 기존 Emitter가 있으면 완료 처리
        SseEmitter existingEmitter = emitters.get(userId);
        if (existingEmitter != null) {
            existingEmitter.complete();
            emitters.remove(userId);
        }

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.put(userId, emitter);

        emitter.onCompletion(() -> {
            emitters.remove(userId);
            logger.info("Emitter completed for userId: {}", userId);
        });
        emitter.onTimeout(() -> {
            emitter.complete();
            emitters.remove(userId);
            logger.info("Emitter timed out for userId: {}", userId);
        });
        emitter.onError((e) -> {
            emitters.remove(userId);
            emitter.completeWithError(e);
            logger.error("Emitter error for userId: {}", userId, e);
        });

        try {
            emitter.send(SseEmitter.event().name("connected").data("Connection established"));
        } catch (IOException e) {
            emitter.completeWithError(e);
            logger.error("Failed to send connection event to userId: {}", userId, e);
        }

        // 주기적인 핑 이벤트 전송
        executor.execute(() -> {
            while (emitters.containsKey(userId)) { // 대체 조건
                try {
                    Thread.sleep(15000); // 15초마다 핑 전송
                    emitter.send(SseEmitter.event().name("ping").data("keep-alive"));
                    logger.info("Ping sent to userId: {}", userId);
                } catch (IOException | InterruptedException e) {
                    emitter.completeWithError(e);
                    emitters.remove(userId);
                    logger.error("Failed to send ping to userId: {}", userId, e);
                    break;
                }
            }
        });

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