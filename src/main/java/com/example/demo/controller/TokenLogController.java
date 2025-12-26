package com.example.demo.controller;

import com.example.demo.entity.TokenLog;
import com.example.demo.service.TokenLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/token-logs")
public class TokenLogController {

    private final TokenLogService service;

    public TokenLogController(TokenLogService service) {
        this.service = service;
    }

    @GetMapping
    public List<TokenLog> getAll() {
        return service.getAll();
    }

    @GetMapping("/token/{tokenId}")
    public List<TokenLog> getByToken(@PathVariable Long tokenId) {
        return service.getByTokenId(tokenId);
    }
}
