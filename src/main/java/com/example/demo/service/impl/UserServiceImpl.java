package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl {
    private final UserRepository repo;
    public UserServiceImpl(UserRepository repo) { this.repo = repo; }

    public User register(User u) {
        if (repo.findByEmail(u.getEmail()).isPresent())
            throw new IllegalArgumentException("Email already exists");
        return repo.save(u);
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
