package com.example.demo.controller;

import com.example.demo.entity.Token;
import com.example.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/create")
    public Token createToken(@RequestBody Token token){
        return tokenService.createToken(token);
    }

    @PutMapping("/{id}/status")
    public Token updateTokenStatus(@PathVariable Long id, @RequestParam String status){
        return tokenService.updateTokenStatus(id, status);
    }

    @GetMapping("/counter/{counterId}/status")
    public List<Token> getTokensByCounterAndStatus(@PathVariable Long counterId, @RequestParam String status){
        return tokenService.getTokensByCounterAndStatus(counterId, status);
    }

    @GetMapping("/number/{tokenNumber}")
    public Token getTokenByNumber(@PathVariable String tokenNumber){
        return tokenService.getTokenByNumber(tokenNumber);
    }

    @GetMapping("/all")
    public List<Token> getAllTokens(){
        return tokenService.getAllTokens();
    }

    @DeleteMapping("/{id}")
    public void deleteToken(@PathVariable Long id){
        tokenService.deleteToken(id);
    }
}
