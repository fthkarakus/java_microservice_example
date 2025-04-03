package com.fatihkarakus.analytics_service.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEvent {
    private String eventType; // CREATED, SENT, READ
    private String notificationId;
    private String userId;
    private String title;
    private String message;
    private LocalDateTime timestamp;
} 