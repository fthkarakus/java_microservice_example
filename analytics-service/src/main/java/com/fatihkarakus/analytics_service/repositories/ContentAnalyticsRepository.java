package com.fatihkarakus.analytics_service.repositories;

import com.fatihkarakus.analytics_service.entities.ContentAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentAnalyticsRepository extends JpaRepository<ContentAnalytics, Long> {
    Optional<ContentAnalytics> findByContentId(String contentId);
} 