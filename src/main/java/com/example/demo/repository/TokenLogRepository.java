package com.example.demo.repository;

import com.example.demo.entity.TokenLog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenLogRepository extends JpaRepository<TokenLog, Long> {

    List<TokenLog> findByToken_IdOrderByLoggedAtAsc(Long tokenId);
}
