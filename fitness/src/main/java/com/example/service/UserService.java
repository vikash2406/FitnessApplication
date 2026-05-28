package com.example.service;

import com.example.model.dtos.RegisterRequest;
import com.example.model.entities.Users;
import com.example.model.response.UserResponse;
import com.example.repository.UserRepository;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse getUserProfile( String userId){
        Users user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not Found !!"));
        return UserResponse.builder().email(user.getEmail())
                .password(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .id(user.getId())
                .build();
    }

    public UserResponse addUserProfile(RegisterRequest registerRequest){
        if(userRepository.existsByEmail(registerRequest.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        Users user = new Users();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setLastName(registerRequest.getLastName());
        user.setFirstName(registerRequest.getFirstName());
        Users savedUser = userRepository.save(user);
        return UserResponse.builder().email(savedUser.getEmail())
                .password(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .createdAt(savedUser.getCreatedAt())
                .updatedAt(savedUser.getUpdatedAt())
                .id(savedUser.getId())
                .build();
    }

    public @Nullable Boolean existbyUserId(String userId) {
        return userRepository.existsById(userId);
    }
}
