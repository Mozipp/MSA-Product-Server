package com.mozipp.product.global.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    // 디자이너용 SSE 구독 엔드포인트
    @GetMapping(value = "/subscribe-designer", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeDesigner(@AuthenticationPrincipal Long designerId) {
        return notificationService.createEmitter(designerId);
    }

    // 모델용 SSE 구독 엔드포인트
    @GetMapping(value = "/subscribe-model", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeModel(@AuthenticationPrincipal Long modelId) {
        return notificationService.createEmitter(modelId);
    }
}