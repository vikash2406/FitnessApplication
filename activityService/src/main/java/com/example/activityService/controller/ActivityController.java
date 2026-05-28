package com.example.activityService.controller;

import com.example.activityService.model.dtos.ActivityRequest;
import com.example.activityService.model.response.ActivityResponse;
import com.example.activityService.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {
    @Autowired
    private ActivityService activityService;


    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<ActivityResponse>> getUserProfile(@PathVariable String userId){
        return ResponseEntity.ok(activityService.getUserProfile(userId));
    }
    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivityById(@PathVariable String activityId){
        return ResponseEntity.ok(activityService.getActivityById(activityId));
    }

    @PostMapping("/add")
    public ResponseEntity<ActivityResponse> addUserProfile( @RequestBody ActivityRequest activityRequest){
        return ResponseEntity.ok(activityService.addUserProfile(activityRequest));
    }
}
