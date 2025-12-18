package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tokenNumber;
    private String status;
    private LocalDateTime issuedAt;
    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn(name = "counter_id")
    @JsonIgnoreProperties("tokens") // prevents infinite loop
    private ServiceCounter serviceCounter;

    // getters & setters
}
