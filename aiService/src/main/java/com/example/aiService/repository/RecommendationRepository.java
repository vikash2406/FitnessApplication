package com.example.aiService.repository;

import com.example.aiService.model.entities.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecommendationRepository extends MongoRepository<Recommendation, String> {
    Optional<Recommendation> findByActivityId(String activityId);
}
