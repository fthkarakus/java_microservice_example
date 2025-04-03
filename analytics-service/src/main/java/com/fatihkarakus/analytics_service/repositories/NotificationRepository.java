package com.fatihkarakus.analytics_service.repositories;

import com.fatihkarakus.analytics_service.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserIdAndReadFalse(String userId);
    List<Notification> findByUserId(String userId);
    List<Notification> findBySentFalse();
} 