package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Token;
import com.example.demo.service.TokenService;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final TokenService service;

    public TokenController(TokenService service) {
        this.service = service;
    }

    @PostMapping("/generate/{counterId}")
    public Token generate(@PathVariable Long counterId) {
        return service.generateToken(counterId);
    }

    @GetMapping("/all")
    public List<Token> all() {
        return service.getAllTokens();
    }
}
