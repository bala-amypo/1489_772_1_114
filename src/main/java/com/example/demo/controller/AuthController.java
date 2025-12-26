package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.config.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserServiceImpl userService;
    private final JwtTokenProvider jwtProvider;

    public AuthController(UserServiceImpl userService, JwtTokenProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User created = userService.register(user);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User u = userService.findByEmail(user.getEmail());
        if (u == null) return ResponseEntity.status(401).body("Invalid credentials");

        // Note: in real app, compare hashed password
        String token = jwtProvider.generateToken(u.getId(), u.getEmail(), "ADMIN");
        return ResponseEntity.ok(token);
    }
}
