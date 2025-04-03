package com.fatihkarakus.analytics_service.controllers;

import com.fatihkarakus.analytics_service.entities.Notification;
import com.fatihkarakus.analytics_service.services.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name = "Notification Management", description = "Notification management APIs")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    @Operation(summary = "Create new notification")
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.createNotification(notification));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get notification by ID")
    public ResponseEntity<Notification> getNotification(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getNotification(id));
    }

    @GetMapping("/user/{userId}/unread")
    @Operation(summary = "Get unread notifications for user")
    public ResponseEntity<List<Notification>> getUnreadNotifications(@PathVariable String userId) {
        return ResponseEntity.ok(notificationService.getUnreadNotifications(userId));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all notifications for user")
    public ResponseEntity<List<Notification>> getAllNotifications(@PathVariable String userId) {
        return ResponseEntity.ok(notificationService.getAllNotifications(userId));
    }

    @PutMapping("/{id}/read")
    @Operation(summary = "Mark notification as read")
    public ResponseEntity<Notification> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.markAsRead(id));
    }

    @PutMapping("/{id}/sent")
    @Operation(summary = "Mark notification as sent")
    public ResponseEntity<Notification> markAsSent(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.markAsSent(id));
    }

    @GetMapping("/unsent")
    @Operation(summary = "Get all unsent notifications")
    public ResponseEntity<List<Notification>> getUnsentNotifications() {
        return ResponseEntity.ok(notificationService.getUnsentNotifications());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete notification")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.ok().build();
    }
} 