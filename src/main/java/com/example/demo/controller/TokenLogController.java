package com.example.demo.controller;

import com.example.demo.entity.TokenLog;
import com.example.demo.service.TokenLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tokenLogs")
public class TokenLogController {

    @Autowired
    private TokenLogService tokenLogService;

    // POST – Create log
    @PostMapping
    public TokenLog createLog(@RequestBody TokenLog tokenLog) {
        return tokenLogService.saveLog(tokenLog);
    }

    // GET – Logs by Token ID
    @GetMapping("/token/{tokenId}")
    public List<TokenLog> getLogsByToken(@PathVariable Long tokenId) {
        return tokenLogService.getLogsByTokenId(tokenId);
    }
}
