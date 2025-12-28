package com.example.demo.controller;

import com.example.demo.dto.StatusUpdateRequest;
import com.example.demo.dto.TokenResponse;
import com.example.demo.entity.Token;
import com.example.demo.service.TokenService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/issue/{counterId}")
    public TokenResponse issue(@PathVariable Long counterId) {
        return map(tokenService.issueToken(counterId));
    }

    @PutMapping("/status/{tokenId}")
    public TokenResponse update(@PathVariable Long tokenId,
                                @RequestBody StatusUpdateRequest request) {
        return map(tokenService.updateStatus(tokenId, request.getStatus()));
    }

    @GetMapping("/{tokenId}")
    public TokenResponse get(@PathVariable Long tokenId) {
        return map(tokenService.getToken(tokenId));
    }

    private TokenResponse map(Token token) {
        TokenResponse r = new TokenResponse();
        r.setId(token.getId());
        r.setTokenNumber(token.getTokenNumber());
        r.setStatus(token.getStatus());
        r.setCounterName(token.getServiceCounter().getCounterName());
        r.setIssuedAt(token.getIssuedAt());
        r.setCompletedAt(token.getCompletedAt());
        return r;
    }
}
