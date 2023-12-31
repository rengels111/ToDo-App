package com.engelsit.restapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany  // User has many ToDos
    @JoinColumn(name = "userId")  // connects both SQL tables
    private Set<ToDo> todos;

    private String secretKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ToDo> getTodos() {
        return todos;
    }

    public void setTodos(Set<ToDo> todos) {
        this.todos = todos;
    }

    public String getSecretKey() {
        return secretKey;
    }

    @JsonIgnore  // to not be able to set a new secretKey via JSON
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
