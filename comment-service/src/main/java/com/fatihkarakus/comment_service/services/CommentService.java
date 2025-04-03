package com.fatihkarakus.comment_service.services;

import com.fatihkarakus.comment_service.entities.Comment;
import com.fatihkarakus.comment_service.entities.CommentStatus;
import com.fatihkarakus.comment_service.events.CommentEvent;
import com.fatihkarakus.comment_service.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final KafkaTemplate<String, CommentEvent> kafkaTemplate;

    @Transactional
    public Comment createComment(Comment comment) {
        Comment savedComment = commentRepository.save(comment);
        
        // Kafka event gönder
        CommentEvent event = new CommentEvent(
            "CREATED",
            savedComment.getId().toString(),
            savedComment.getContentId(),
            savedComment.getUserId(),
            LocalDateTime.now()
        );
        kafkaTemplate.send("comment-events", event);
        
        return savedComment;
    }

    @Transactional
    public Comment updateComment(Long id, String content) {
        Comment existingComment = commentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comment not found"));
        
        existingComment.setContent(content);
        existingComment.setUpdatedAt(LocalDateTime.now());
        
        Comment updatedComment = commentRepository.save(existingComment);
        
        // Kafka event gönder
        CommentEvent event = new CommentEvent(
            "UPDATED",
            updatedComment.getId().toString(),
            updatedComment.getContentId(),
            updatedComment.getUserId(),
            LocalDateTime.now()
        );
        kafkaTemplate.send("comment-events", event);
        
        return updatedComment;
    }

    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comment not found"));
            
        commentRepository.delete(comment);
        
        // Kafka event gönder
        CommentEvent event = new CommentEvent(
            "DELETED",
            comment.getId().toString(),
            comment.getContentId(),
            comment.getUserId(),
            LocalDateTime.now()
        );
        kafkaTemplate.send("comment-events", event);
    }

    @Transactional(readOnly = true)
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Comment getComment(Long id) {
        return commentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsByContent(String contentId) {
        return commentRepository.findByContentId(contentId);
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsByUser(String userId) {
        return commentRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<Comment> getPendingComments() {
        return commentRepository.findByStatus(CommentStatus.PENDING);
    }

    @Transactional
    public Comment moderateComment(Long id, CommentStatus status, String moderatorId) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comment not found"));
            
        comment.setStatus(status);
        comment.setModeratedAt(LocalDateTime.now());
        comment.setModeratedBy(moderatorId);
        
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment approveComment(Long id) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comment not found"));
            
        comment.setStatus(CommentStatus.APPROVED);
        comment.setModeratedAt(LocalDateTime.now());
        
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment rejectComment(Long id) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comment not found"));
            
        comment.setStatus(CommentStatus.REJECTED);
        comment.setModeratedAt(LocalDateTime.now());
        
        return commentRepository.save(comment);
    }
} 