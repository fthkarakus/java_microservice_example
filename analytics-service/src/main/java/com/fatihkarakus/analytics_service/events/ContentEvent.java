package com.fatihkarakus.analytics_service.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentEvent {
    private String eventType; // CREATED, UPDATED, DELETED
    private String contentId;
    private String title;
    private String userId;
    private LocalDateTime timestamp;
} 