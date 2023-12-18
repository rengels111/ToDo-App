package com.engelsit.restapp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity  // annotation for jpa, hibernate, sql
public class ToDo {

    @Id  // This is the unique id for the sql database.
    @GeneratedValue(strategy = GenerationType.AUTO)  // id will be automatically raised by one.
    private Integer id;
    private String description;
    private boolean isDone;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
