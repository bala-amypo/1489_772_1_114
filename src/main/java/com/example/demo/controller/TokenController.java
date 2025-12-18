package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Token;
import com.example.demo.service.TokenService;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    // Generate token using counter ID
    @PostMapping("/generate/{counterId}")
    public Token generateToken(@PathVariable Long counterId) {
        return tokenService.generateToken(counterId);
    }

    // Get all tokens
    @GetMapping
    public List<Token> getAllTokens() {
        return tokenService.getAllTokens();
    }
}
