package com.fatihkarakus.content_service.services;

import com.fatihkarakus.content_service.entities.Category;
import com.fatihkarakus.content_service.entities.Content;
import com.fatihkarakus.content_service.entities.Tag;
import com.fatihkarakus.content_service.events.ContentEvent;
import com.fatihkarakus.content_service.repositories.CategoryRepository;
import com.fatihkarakus.content_service.repositories.ContentRepository;
import com.fatihkarakus.content_service.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final KafkaTemplate<String, ContentEvent> kafkaTemplate;

    @Transactional
    public Content createContent(Content content, Set<String> categoryNames, Set<String> tagNames) {
        // Kategorileri bul veya oluştur
        Set<Category> categories = categoryNames.stream()
            .map(name -> categoryRepository.findByName(name)
                .orElseGet(() -> {
                    Category category = new Category();
                    category.setName(name);
                    return categoryRepository.save(category);
                }))
            .collect(Collectors.toSet());

        // Etiketleri bul veya oluştur
        Set<Tag> tags = tagNames.stream()
            .map(name -> tagRepository.findByName(name)
                .orElseGet(() -> {
                    Tag tag = new Tag();
                    tag.setName(name);
                    return tagRepository.save(tag);
                }))
            .collect(Collectors.toSet());

        content.setCategories(categories);
        content.setTags(tags);
        Content savedContent = contentRepository.save(content);
        
        // Kafka event gönder
        ContentEvent event = new ContentEvent(
            "CREATED",
            savedContent.getId().toString(),
            savedContent.getTitle(),
            savedContent.getUserId(),
            LocalDateTime.now()
        );
        kafkaTemplate.send("content-events", event);
        
        return savedContent;
    }

    @Transactional
    public Content updateContent(Long id, Content content) {
        Content existingContent = contentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Content not found"));
        
        existingContent.setTitle(content.getTitle());
        existingContent.setBody(content.getBody());
        existingContent.setUpdatedAt(LocalDateTime.now());
        
        Content updatedContent = contentRepository.save(existingContent);
        
        // Kafka event gönder
        ContentEvent event = new ContentEvent(
            "UPDATED",
            updatedContent.getId().toString(),
            updatedContent.getTitle(),
            updatedContent.getUserId(),
            LocalDateTime.now()
        );
        kafkaTemplate.send("content-events", event);
        
        return updatedContent;
    }

    @Transactional
    public void deleteContent(Long id) {
        Content content = contentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Content not found"));
            
        contentRepository.delete(content);
        
        // Kafka event gönder
        ContentEvent event = new ContentEvent(
            "DELETED",
            content.getId().toString(),
            content.getTitle(),
            content.getUserId(),
            LocalDateTime.now()
        );
        kafkaTemplate.send("content-events", event);
    }

    @Transactional(readOnly = true)
    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Content getContent(Long id) {
        return contentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Content not found"));
    }

    @Transactional(readOnly = true)
    public List<Content> getContentsByAuthor(String authorId) {
        return contentRepository.findByUserId(authorId);
    }

    @Transactional(readOnly = true)
    public List<Content> getPublishedContents() {
        return contentRepository.findByPublishedTrue();
    }

    @Transactional
    public Content publishContent(Long id) {
        Content content = contentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Content not found"));
            
        content.setPublished(true);
        content.setPublishedAt(LocalDateTime.now());
        
        return contentRepository.save(content);
    }

    @Transactional
    public Content unpublishContent(Long id) {
        Content content = contentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Content not found"));
            
        content.setPublished(false);
        content.setPublishedAt(null);
        
        return contentRepository.save(content);
    }
} 