package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class QueuePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Token token;

    @Column(nullable = false)
    private Integer position;
}
