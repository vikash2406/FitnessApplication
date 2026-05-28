package com.example.aiService.controller;

import com.example.aiService.model.entities.Recommendation;
import com.example.aiService.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;


    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recommendation> getActivityRecommendation(String activityId) {
        Recommendation recommendation = recommendationService.getActivityRecommendation(activityId);
        return ResponseEntity.ok(recommendation);
    }
}
