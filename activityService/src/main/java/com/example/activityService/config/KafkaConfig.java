package com.example.activityService.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.converter.MessageConverter;

import java.util.Queue;

@Configuration
public class KafkaConfig {

//    @Bean
//    public NewTopic createTopic() {
//
//        return TopicBuilder.name("my-topic")
//                .partitions(3)
//                .replicas(1)
//                .build();
//    }

//    @Bean
//    public MessageConverter messageConverter() {
//        return new  Jackson2JsonMessageConverter();
//    }
}
