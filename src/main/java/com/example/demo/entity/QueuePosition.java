
package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "queue_position")
public class QueuePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer position;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "token_id", unique = true)
    private Token token;
    
    public QueuePosition() {}
    
    public QueuePosition(Integer position, Token token) {
        this.position = position;
        this.token = token;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { 
        if (position != null && position < 1) {
            throw new IllegalArgumentException("Position must be >= 1");
        }
        this.position = position; 
    }
    
    public Token getToken() { return token; }
    public void setToken(Token token) { this.token = token; }
}
