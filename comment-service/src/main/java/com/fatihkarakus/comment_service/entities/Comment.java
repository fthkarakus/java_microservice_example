package com.fatihkarakus.comment_service.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String contentId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String username;

    private Long parentId;

    @Enumerated(EnumType.STRING)
    private CommentStatus status = CommentStatus.PENDING;

    private boolean isSpam = false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime moderatedAt;
    private String moderatedBy;
} 