package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Token;
import com.example.demo.service.TokenService;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public Token createToken(@RequestBody Token token) {
        return tokenService.createToken(token);
    }

    @GetMapping("/{id}")
    public Token getToken(@PathVariable Long id) {
        return tokenService.getToken(id);
    }
}
