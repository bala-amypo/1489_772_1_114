package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.QueueEntity;

public interface QueueRepository extends JpaRepository<QueueEntity, Long> {
}
