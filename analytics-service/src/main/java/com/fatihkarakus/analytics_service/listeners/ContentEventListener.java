package com.fatihkarakus.analytics_service.listeners;

import com.fatihkarakus.analytics_service.events.ContentEvent;
import com.fatihkarakus.analytics_service.services.AnalyticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContentEventListener {
    private final AnalyticsService analyticsService;

    @KafkaListener(topics = "content-events", groupId = "analytics-group")
    public void handleContentEvent(ContentEvent event) {
        log.info("Received content event: {}", event);
        
        switch (event.getEventType()) {
            case "CREATED":
                analyticsService.incrementContentCount(event.getUserId());
                break;
            case "DELETED":
                analyticsService.decrementContentCount(event.getUserId());
                break;
            default:
                log.info("Unhandled event type: {}", event.getEventType());
        }
    }
} 