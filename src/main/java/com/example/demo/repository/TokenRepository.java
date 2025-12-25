package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> findByServiceCounter_IdAndStatusOrderByIssuedAtAsc(
            Long counterId, String status);

    Optional<Token> findByTokenNumber(String tokenNumber);
}
