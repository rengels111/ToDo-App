package com.engelsit.restapp.controller;

import com.engelsit.restapp.entity.User;
import com.engelsit.restapp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    private ResponseEntity<User> register(@RequestBody User newUser) {
        var registeredUser = userRepository.save(newUser);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    private ResponseEntity<User> getUser(@RequestParam(value = "id") int id) {
        Optional<User> userInDb = userRepository.findById(id);

        if (userInDb.isPresent()) {
            return new ResponseEntity<>(userInDb.get(), HttpStatus.OK);
        }
        return new ResponseEntity("User not found with id " + id, HttpStatus.NOT_FOUND);
    }
}
