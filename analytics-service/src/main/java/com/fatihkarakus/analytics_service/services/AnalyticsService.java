package com.fatihkarakus.analytics_service.services;

import com.fatihkarakus.analytics_service.entities.ContentAnalytics;
import com.fatihkarakus.analytics_service.repositories.ContentAnalyticsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {
    private final ContentAnalyticsRepository contentAnalyticsRepository;

    @Transactional
    public ContentAnalytics incrementViewCount(String contentId) {
        ContentAnalytics analytics = getOrCreateAnalytics(contentId);
        analytics.setViewCount(analytics.getViewCount() + 1);
        analytics.setUpdatedAt(LocalDateTime.now());
        return contentAnalyticsRepository.save(analytics);
    }

    @Transactional
    public ContentAnalytics incrementCommentCount(String contentId) {
        ContentAnalytics analytics = getOrCreateAnalytics(contentId);
        analytics.setCommentCount(analytics.getCommentCount() + 1);
        analytics.setUpdatedAt(LocalDateTime.now());
        return contentAnalyticsRepository.save(analytics);
    }

    @Transactional
    public ContentAnalytics decrementCommentCount(String contentId) {
        ContentAnalytics analytics = getOrCreateAnalytics(contentId);
        if (analytics.getCommentCount() > 0) {
            analytics.setCommentCount(analytics.getCommentCount() - 1);
            analytics.setUpdatedAt(LocalDateTime.now());
            return contentAnalyticsRepository.save(analytics);
        }
        return analytics;
    }

    @Transactional
    public ContentAnalytics incrementLikeCount(String contentId) {
        ContentAnalytics analytics = getOrCreateAnalytics(contentId);
        analytics.setLikeCount(analytics.getLikeCount() + 1);
        analytics.setUpdatedAt(LocalDateTime.now());
        return contentAnalyticsRepository.save(analytics);
    }

    @Transactional
    public ContentAnalytics incrementShareCount(String contentId) {
        ContentAnalytics analytics = getOrCreateAnalytics(contentId);
        analytics.setShareCount(analytics.getShareCount() + 1);
        analytics.setUpdatedAt(LocalDateTime.now());
        return contentAnalyticsRepository.save(analytics);
    }

    @Transactional
    public ContentAnalytics incrementContentCount(String userId) {
        // Bu metod kullanıcı bazlı içerik sayısını artırır
        // Şu an için implementasyon yok, ileride eklenebilir
        return null;
    }

    @Transactional
    public ContentAnalytics decrementContentCount(String userId) {
        // Bu metod kullanıcı bazlı içerik sayısını azaltır
        // Şu an için implementasyon yok, ileride eklenebilir
        return null;
    }

    @Transactional
    public ContentAnalytics incrementUserCommentCount(String userId) {
        // Bu metod kullanıcı bazlı yorum sayısını artırır
        // Şu an için implementasyon yok, ileride eklenebilir
        return null;
    }

    @Transactional
    public ContentAnalytics decrementUserCommentCount(String userId) {
        // Bu metod kullanıcı bazlı yorum sayısını azaltır
        // Şu an için implementasyon yok, ileride eklenebilir
        return null;
    }

    @Transactional(readOnly = true)
    public ContentAnalytics getAnalytics(String contentId) {
        return contentAnalyticsRepository.findByContentId(contentId)
                .orElseGet(() -> createNewAnalytics(contentId));
    }

    private ContentAnalytics getOrCreateAnalytics(String contentId) {
        return contentAnalyticsRepository.findByContentId(contentId)
                .orElseGet(() -> createNewAnalytics(contentId));
    }

    private ContentAnalytics createNewAnalytics(String contentId) {
        ContentAnalytics analytics = new ContentAnalytics();
        analytics.setContentId(contentId);
        analytics.setViewCount(0L);
        analytics.setCommentCount(0L);
        analytics.setLikeCount(0L);
        analytics.setShareCount(0L);
        analytics.setCreatedAt(LocalDateTime.now());
        analytics.setUpdatedAt(LocalDateTime.now());
        return contentAnalyticsRepository.save(analytics);
    }
} 