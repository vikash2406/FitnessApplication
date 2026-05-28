package com.example.activityService.model.dtos;

import com.example.activityService.model.ActivityType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Data
public class ActivityRequest {
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer cloriesBurned;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;


}
