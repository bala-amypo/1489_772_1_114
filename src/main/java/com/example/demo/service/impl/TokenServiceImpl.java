package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Token createToken(Token token) {
        if(tokenRepository.findByTokenNumber(token.getTokenNumber()) != null){
            throw new RuntimeException("Token number already exists");
        }
        token.setStatus("WAITING");
        return tokenRepository.save(token);
    }

    @Override
    public Token updateTokenStatus(Long tokenId, String status) {
        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));
        token.setStatus(status);
        return tokenRepository.save(token);
    }

    @Override
    public List<Token> getTokensByCounterAndStatus(Long counterId, String status) {
        return tokenRepository.findByServiceCounter_IdAndStatusOrderByIssuedAtAsc(counterId, status);
    }

    @Override
    public Token getTokenByNumber(String tokenNumber) {
        return tokenRepository.findByTokenNumber(tokenNumber);
    }

    @Override
    public List<Token> getAllTokens() {
        return tokenRepository.findAll();
    }

    @Override
    public void deleteToken(Long tokenId) {
        tokenRepository.deleteById(tokenId);
    }
}
