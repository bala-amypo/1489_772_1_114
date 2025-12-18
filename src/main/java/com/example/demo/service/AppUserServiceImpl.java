package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppUserRepository;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository repo;

    public AppUserServiceImpl(AppUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public AppUser saveUser(AppUser user) {
        return repo.save(user);
    }
}
