package com.example.aiService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GeminiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public String getAnswer(String question) {
        Map<String,Object> requestBody = Map.of("contents",new Object[]{
                Map.of("parts",new Object[]{
                        Map.of("text", question)
                })
        }
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-goog-api-key", geminiApiKey);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(requestBody, headers);

        String response = restTemplate.postForObject(
                geminiApiUrl ,
                entity,
                String.class
        );
        return response;
    }
}
