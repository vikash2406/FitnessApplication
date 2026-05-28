package com.example.aiService.service;

import com.example.aiService.model.entities.Recommendation;
import com.example.aiService.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    public Recommendation getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId)
                .orElseThrow(() -> new RuntimeException("Recommendation not found for activityId: " + activityId));
    }
}
