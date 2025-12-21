package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Token;
import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByTokenNumber(String tokenNumber);
    List<Token> findByServiceCounter_IdAndStatusOrderByIssuedAtAsc(Long counterId, String status);
}
