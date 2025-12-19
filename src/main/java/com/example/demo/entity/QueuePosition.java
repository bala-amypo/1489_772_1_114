package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class QueuePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "token_id", nullable = false)
    private Token token;

    private Integer position;

    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void updateTime() {
        this.updatedAt = LocalDateTime.now();
    }

    // getters & setters
}
