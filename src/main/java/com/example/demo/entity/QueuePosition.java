package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.Token;
import com.example.demo.service.TokenService;
import java.util.List;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    private final TokenService tokenService;

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    public Token createToken(@RequestBody Token token) {
        return tokenService.createToken(token);
    }

    @GetMapping("/{id}")
    public Token getToken(@PathVariable Long id) {
        return tokenService.getToken(id);
    }

    @GetMapping
    public List<Token> getAllTokens() {
        return tokenService.getAllTokens();
    }

    @PutMapping("/{id}")
    public Token updateToken(@PathVariable Long id, @RequestBody Token token) {
        return tokenService.updateToken(id, token);
    }

    @DeleteMapping("/{id}")
    public void deleteToken(@PathVariable Long id) {
        tokenService.deleteToken(id);
    }
}
