package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.AppUser;
import com.example.demo.service.AppUserService;

@RestController
@RequestMapping("/user")
public class AppUserController {

    private final AppUserService service;

    public AppUserController(AppUserService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public AppUser add(@RequestBody AppUser user) {
        return service.saveUser(user);
    }
}
