package com.fatihkarakus.comment_service.controllers;

import com.fatihkarakus.comment_service.entities.Comment;
import com.fatihkarakus.comment_service.entities.CommentStatus;
import com.fatihkarakus.comment_service.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Tag(name = "Comment Management", description = "Comment management APIs")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @Operation(summary = "Create new comment")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.createComment(comment));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get comment by ID")
    public ResponseEntity<Comment> getComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getComment(id));
    }

    @GetMapping("/content/{contentId}")
    @Operation(summary = "Get comments by content")
    public ResponseEntity<List<Comment>> getCommentsByContent(@PathVariable String contentId) {
        return ResponseEntity.ok(commentService.getCommentsByContent(contentId));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get comments by user")
    public ResponseEntity<List<Comment>> getCommentsByUser(@PathVariable String userId) {
        return ResponseEntity.ok(commentService.getCommentsByUser(userId));
    }

    @GetMapping("/pending")
    @Operation(summary = "Get pending comments")
    public ResponseEntity<List<Comment>> getPendingComments() {
        return ResponseEntity.ok(commentService.getPendingComments());
    }

    @PutMapping("/{id}/moderate")
    @Operation(summary = "Moderate comment")
    public ResponseEntity<Comment> moderateComment(
            @PathVariable Long id,
            @RequestParam CommentStatus status,
            @RequestParam String moderatorId) {
        return ResponseEntity.ok(commentService.moderateComment(id, status, moderatorId));
    }

    @PutMapping("/{id}/approve")
    @Operation(summary = "Approve comment")
    public ResponseEntity<Comment> approveComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.approveComment(id));
    }

    @PutMapping("/{id}/reject")
    @Operation(summary = "Reject comment")
    public ResponseEntity<Comment> rejectComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.rejectComment(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update comment")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Long id,
            @RequestBody String content) {
        return ResponseEntity.ok(commentService.updateComment(id, content));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete comment")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
} 