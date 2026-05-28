package com.example.aiService.model.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "recommendations")
@Data
@Builder
public class Recommendation {
    private String id;
    private String activityId;
    private String userId;
    private String activityType;
    private String recommendation;
    private List<String> improvements;
    private List<String> safetyMeasures;
    private List<String> suggestions;
    private LocalDateTime createdAt;


}
