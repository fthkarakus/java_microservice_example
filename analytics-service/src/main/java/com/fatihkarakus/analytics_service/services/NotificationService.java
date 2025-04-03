package com.fatihkarakus.analytics_service.services;

import com.fatihkarakus.analytics_service.entities.Notification;
import com.fatihkarakus.analytics_service.repositories.NotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Transactional
    public Notification createNotification(Notification notification) {
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    @Transactional(readOnly = true)
    public Notification getNotification(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Notification> getUnreadNotifications(String userId) {
        return notificationRepository.findByUserIdAndReadFalse(userId);
    }

    @Transactional(readOnly = true)
    public List<Notification> getAllNotifications(String userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Transactional
    public Notification markAsRead(Long id) {
        Notification notification = getNotification(id);
        notification.setRead(true);
        notification.setReadAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    @Transactional
    public Notification markAsSent(Long id) {
        Notification notification = getNotification(id);
        notification.setSent(true);
        notification.setSentAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    @Transactional(readOnly = true)
    public List<Notification> getUnsentNotifications() {
        return notificationRepository.findBySentFalse();
    }

    @Transactional
    public void deleteNotification(Long id) {
        Notification notification = getNotification(id);
        notificationRepository.delete(notification);
    }
} 