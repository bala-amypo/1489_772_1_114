package com.example.demo.repository;

import com.example.demo.entity.QueuePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QueuePositionRepository extends JpaRepository<QueuePosition, Long> {
    List<QueuePosition> findByToken_IdOrderByUpdatedAtAsc(Long tokenId);
}
