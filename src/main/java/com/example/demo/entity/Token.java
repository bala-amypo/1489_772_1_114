package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String tokenNumber;

    private String status = "WAITING";

    @ManyToOne
    private ServiceCounter serviceCounter;

    private LocalDateTime issuedAt = LocalDateTime.now();
    private LocalDateTime completedAt;
}
