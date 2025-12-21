package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.TokenLog;
import com.example.demo.service.TokenLogService;

@RestController
@RequestMapping("/tokenlogs")
public class TokenLogController {

    private final TokenLogService service;

    public TokenLogController(TokenLogService service) {
        this.service = service;
    }

    @PostMapping
    public TokenLog create(@RequestBody TokenLog log) {
        return service.save(log);
    }
}
