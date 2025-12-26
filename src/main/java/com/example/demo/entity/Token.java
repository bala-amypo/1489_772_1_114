package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tokenNumber;

    private String status = "WAITING";

    private LocalDateTime issuedAt = LocalDateTime.now();
    private LocalDateTime completedAt;

    @ManyToOne
    private ServiceCounter serviceCounter;
}
