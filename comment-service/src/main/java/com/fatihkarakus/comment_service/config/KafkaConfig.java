package com.fatihkarakus.comment_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.topic.comment-events}")
    private String commentEventsTopic;

    @Bean
    public NewTopic commentEventsTopic() {
        return TopicBuilder.name(commentEventsTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }
} 