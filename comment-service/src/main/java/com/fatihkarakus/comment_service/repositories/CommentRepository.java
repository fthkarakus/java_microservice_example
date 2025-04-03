package com.fatihkarakus.comment_service.repositories;

import com.fatihkarakus.comment_service.entities.Comment;
import com.fatihkarakus.comment_service.entities.CommentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByContentId(String contentId);
    List<Comment> findByUserId(String userId);
    List<Comment> findByStatus(CommentStatus status);
    List<Comment> findByParentId(Long parentId);
    boolean existsByContentIdAndUserIdAndIsSpamTrue(String contentId, String userId);
} 