package com.fatihkarakus.analytics_service.listeners;

import com.fatihkarakus.analytics_service.events.CommentEvent;
import com.fatihkarakus.analytics_service.services.AnalyticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentEventListener {
    private final AnalyticsService analyticsService;

    @KafkaListener(topics = "comment-events", groupId = "analytics-group")
    public void handleCommentEvent(CommentEvent event) {
        log.info("Received comment event: {}", event);
        
        switch (event.getEventType()) {
            case "CREATED":
                analyticsService.incrementCommentCount(event.getContentId());
                analyticsService.incrementUserCommentCount(event.getUserId());
                break;
            case "DELETED":
                analyticsService.decrementCommentCount(event.getContentId());
                analyticsService.decrementUserCommentCount(event.getUserId());
                break;
            default:
                log.info("Unhandled event type: {}", event.getEventType());
        }
    }
} 