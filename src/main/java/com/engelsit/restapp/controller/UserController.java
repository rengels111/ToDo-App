package com.engelsit.restapp.controller;

import com.engelsit.restapp.entity.User;
import com.engelsit.restapp.repository.ToDoRepository;
import com.engelsit.restapp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final ToDoRepository toDoRepository;

    public UserController(UserRepository userRepository, ToDoRepository toDoRepository) {
        this.userRepository = userRepository;
        this.toDoRepository = toDoRepository;
    }

    @PostMapping("/register")
    private ResponseEntity<User> register(@RequestBody User newUser) {
        // generate secretKey:
        newUser.setSecretKey(UUID.randomUUID().toString());

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

    @GetMapping("/user/all")
    private ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> allUsers = userRepository.findAll();

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/validate")
    private ResponseEntity<String> validate(@RequestParam(value = "email") String email,
                                          @RequestParam(value = "password") String password) {

        var validUser = userRepository.findByEmailAndPassword(email, password);

        if (validUser.isPresent()) {
            return new ResponseEntity<>("API secret: " + validUser.get().getSecretKey(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Wrong credential / no account found", HttpStatus.NOT_FOUND);
    }


    // In progress. I have to find a way how to delete the todos of this user first.
    @DeleteMapping("/user")
    private ResponseEntity<User> delete(@RequestParam(value = "id") int id) {
        Optional<User> userInDb = userRepository.findById(id);

        if (userInDb.isPresent()) {
            toDoRepository.deleteAll(userInDb.get().getTodos());
            userRepository.deleteById(id);
            return new ResponseEntity("User deleted with id " + id, HttpStatus.OK);
        }
        return new ResponseEntity("User not found with id " + id, HttpStatus.NOT_FOUND);
    }
}
