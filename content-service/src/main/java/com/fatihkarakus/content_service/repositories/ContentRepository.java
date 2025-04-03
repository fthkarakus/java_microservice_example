package com.fatihkarakus.content_service.repositories;

import com.fatihkarakus.content_service.entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByUserId(String authorId);
    List<Content> findByPublishedTrue();
} 