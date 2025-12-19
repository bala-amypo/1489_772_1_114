package com.example.demo.controller;

import com.example.demo.entity.TokenLog;
import com.example.demo.service.TokenLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/token-logs")
public class TokenLogController {

    @Autowired
    private TokenLogService tokenLogService;

    @PostMapping
    public TokenLog createTokenLog(@RequestBody TokenLog tokenLog) {
        return tokenLogService.createTokenLog(tokenLog);
    }

    @GetMapping
    public List<TokenLog> getAllTokenLogs() {
        return tokenLogService.getAllTokenLogs();
    }

    @GetMapping("/{id}")
    public TokenLog getTokenLogById(@PathVariable Long id) {
        return tokenLogService.getTokenLogById(id);
    }

    @GetMapping("/token/{tokenId}")
    public List<TokenLog> getLogsByTokenId(@PathVariable Long tokenId) {
        return tokenLogService.getTokenLogsByTokenId(tokenId);
    }
}
