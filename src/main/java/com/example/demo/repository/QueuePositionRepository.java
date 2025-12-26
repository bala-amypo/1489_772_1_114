package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.QueuePosition;

@Repository
public interface QueuePositionRepository extends JpaRepository<QueuePosition, Long> {
}
