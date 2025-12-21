package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Token;
import com.example.demo.service.TokenService;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    private final TokenService service;

    public TokenController(TokenService service) {
        this.service = service;
    }

    @PostMapping
    public Token create(@RequestBody Token token) {
        return service.save(token);
    }
}
