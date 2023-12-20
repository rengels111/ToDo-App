package com.engelsit.restapp.controller;

import com.engelsit.restapp.entity.User;
import com.engelsit.restapp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    private ResponseEntity<User> register(@RequestBody User newUser) {
        userRepository.save(newUser);
        return null;
    }
}
