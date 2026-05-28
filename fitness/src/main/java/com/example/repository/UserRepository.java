package com.example.repository;

import com.example.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,String> {
    boolean existsByEmail(String email);
}
