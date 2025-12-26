package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    User authenticate(String email, String password);
}
