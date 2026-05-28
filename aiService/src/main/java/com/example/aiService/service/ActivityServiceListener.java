package com.example.aiService.service;

import com.example.aiService.model.dtos.ActivityEvent;
import com.example.aiService.model.entities.Recommendation;
import com.example.aiService.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceListener {
    @Autowired
    private ActivityAIService activityAIService;
    @Autowired
    private RecommendationRepository recommendationRepository;
    @KafkaListener(topics = "activity-topic", groupId = "my-new-group")
    public void listen(ActivityEvent activityEvent){

        System.out.println(activityEvent);
        Recommendation recommendation = activityAIService.getActivitySuggestion(activityEvent);
        recommendationRepository.save(recommendation);

    }
}
