package com.example.demo.controller;

import com.example.demo.config.JwtTokenProvider;
import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User created = userService.register(user);

        AuthResponse response = new AuthResponse();
        response.setToken(jwtTokenProvider.generateToken(
                created.getId(), created.getEmail(), "USER"));
        response.setEmail(created.getEmail());
        response.setRole("USER");
        return response;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        User user = userService.findByEmail(request.getEmail());

        if (!("encoded_" + request.getPassword()).equals(user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        AuthResponse response = new AuthResponse();
        response.setToken(jwtTokenProvider.generateToken(
                user.getId(), user.getEmail(), "USER"));
        response.setEmail(user.getEmail());
        response.setRole("USER");
        return response;
    }
}
