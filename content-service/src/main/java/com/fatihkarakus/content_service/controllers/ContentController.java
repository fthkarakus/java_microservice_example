package com.fatihkarakus.content_service.controllers;

import com.fatihkarakus.content_service.entities.Content;
import com.fatihkarakus.content_service.services.ContentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
@Tag(name = "Content Management", description = "Content management APIs")
public class ContentController {
    private final ContentService contentService;

    @PostMapping
    @Operation(summary = "Create new content")
    public ResponseEntity<Content> createContent(
            @RequestBody Content content,
            @RequestParam Set<String> categories,
            @RequestParam Set<String> tags) {
        return ResponseEntity.ok(contentService.createContent(content, categories, tags));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get content by ID")
    public ResponseEntity<Content> getContent(@PathVariable Long id) {
        return ResponseEntity.ok(contentService.getContent(id));
    }

    @GetMapping("/author/{authorId}")
    @Operation(summary = "Get contents by author")
    public ResponseEntity<List<Content>> getContentsByAuthor(@PathVariable String authorId) {
        return ResponseEntity.ok(contentService.getContentsByAuthor(authorId));
    }

    @GetMapping("/published")
    @Operation(summary = "Get all published contents")
    public ResponseEntity<List<Content>> getPublishedContents() {
        return ResponseEntity.ok(contentService.getPublishedContents());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update content")
    public ResponseEntity<Content> updateContent(
            @PathVariable Long id,
            @RequestBody Content contentDetails) {
        return ResponseEntity.ok(contentService.updateContent(id, contentDetails));
    }

    @PutMapping("/{id}/publish")
    @Operation(summary = "Publish content")
    public ResponseEntity<Content> publishContent(@PathVariable Long id) {
        return ResponseEntity.ok(contentService.publishContent(id));
    }

    @PutMapping("/{id}/unpublish")
    @Operation(summary = "Unpublish content")
    public ResponseEntity<Content> unpublishContent(@PathVariable Long id) {
        return ResponseEntity.ok(contentService.unpublishContent(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete content")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.ok().build();
    }
} 