package com.example.activityService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class UserValidateService {

//    @Autowired
//    private WebClient webClient;
    @Autowired
    private RestTemplate restTemplate;

    public boolean validateUser(String userId) {
        try {
            return restTemplate.getForObject("http://localhost:9089/api/users/{userId}/validate", Boolean.class, userId);
        }catch (Exception res){
            throw new RuntimeException("Invalid Request !!! " + userId);
        }
    }

}