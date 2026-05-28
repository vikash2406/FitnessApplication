package com.example.activityService.service;

import com.example.activityService.Repository.ActivityRepository;
import com.example.activityService.model.dtos.ActivityRequest;
import com.example.activityService.model.entities.Activity;
import com.example.activityService.model.response.ActivityResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private KafkaTemplate<String, Activity> kafkaTemplate;
    @Autowired
    private UserValidateService userValidateService;

    public ActivityResponse addUserProfile(ActivityRequest activityRequest){
        boolean isValidUser = userValidateService.validateUser(activityRequest.getUserId());
        if(!isValidUser){
            throw new RuntimeException("Invalid User with userId: " + activityRequest.getUserId());
        }
        Activity activity = new Activity();
        activity.setUserId(activityRequest.getUserId());
        activity.setType(activityRequest.getType());
        activity.setDuration(activityRequest.getDuration());
        activity.setCaloriesBurned(activityRequest.getCloriesBurned());
        activity.setAdditionalMetrics(activityRequest.getAdditionalMetrics());
        Activity savedActivity = activityRepository.save(activity);
        try {
            kafkaTemplate.send("activity-topic", savedActivity);
        } catch (Exception e) {
            System.err.println("Failed to send message to Kafka: " + e.getMessage());
        }
        return mapToResponse(savedActivity);
    }

    public List<ActivityResponse> getUserProfile(String userId){
        List<Activity> activities = activityRepository.findByUserId(userId);
        return activities.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ActivityResponse getActivityById(String activityId) {
        return activityRepository.findById(activityId)
                .map(this::mapToResponse)
                .orElseThrow(()->new RuntimeException("Activity not found +"+ activityId));
    }
    private ActivityResponse mapToResponse(Activity savedActivity){
        return ActivityResponse.builder().id(savedActivity.getId())
                .type(savedActivity.getType())
                .userId(savedActivity.getUserId())
                .duration(savedActivity.getDuration())
                .cloriesBurned(savedActivity.getCaloriesBurned())
                .startTime(savedActivity.getStartTime())
                .additionalMetrics(savedActivity.getAdditionalMetrics())
                .createdAt(savedActivity.getCreatedAt())
                .updatedAt(savedActivity.getUpdateddAt())
                .build();
    }
}
