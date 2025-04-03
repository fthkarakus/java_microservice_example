package com.fatihkarakus.comment_service.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEvent {
    private String eventType; // CREATED, UPDATED, DELETED
    private String commentId;
    private String contentId;
    private String userId;
    private LocalDateTime timestamp;
} 