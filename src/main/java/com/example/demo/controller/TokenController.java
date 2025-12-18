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

    @PostMapping
    public Token createToken(@RequestBody Token token) {
        return tokenService.createToken(token);
    }

    @GetMapping
    public List<Token> getTokens() {
        return tokenService.getAllTokens();
    }
}
