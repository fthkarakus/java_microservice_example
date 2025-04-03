package com.fatihkarakus.analytics_service.controllers;

import com.fatihkarakus.analytics_service.entities.ContentAnalytics;
import com.fatihkarakus.analytics_service.services.AnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
@Tag(name = "Analytics Management", description = "Content analytics management APIs")
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    @PostMapping("/content/{contentId}/view")
    @Operation(summary = "Increment view count for content")
    public ResponseEntity<ContentAnalytics> incrementViewCount(@PathVariable String contentId) {
        return ResponseEntity.ok(analyticsService.incrementViewCount(contentId));
    }

    @PostMapping("/content/{contentId}/comment")
    @Operation(summary = "Increment comment count for content")
    public ResponseEntity<ContentAnalytics> incrementCommentCount(@PathVariable String contentId) {
        return ResponseEntity.ok(analyticsService.incrementCommentCount(contentId));
    }

    @PostMapping("/content/{contentId}/like")
    @Operation(summary = "Increment like count for content")
    public ResponseEntity<ContentAnalytics> incrementLikeCount(@PathVariable String contentId) {
        return ResponseEntity.ok(analyticsService.incrementLikeCount(contentId));
    }

    @PostMapping("/content/{contentId}/share")
    @Operation(summary = "Increment share count for content")
    public ResponseEntity<ContentAnalytics> incrementShareCount(@PathVariable String contentId) {
        return ResponseEntity.ok(analyticsService.incrementShareCount(contentId));
    }

    @GetMapping("/content/{contentId}")
    @Operation(summary = "Get analytics for content")
    public ResponseEntity<ContentAnalytics> getAnalytics(@PathVariable String contentId) {
        return ResponseEntity.ok(analyticsService.getAnalytics(contentId));
    }
} 