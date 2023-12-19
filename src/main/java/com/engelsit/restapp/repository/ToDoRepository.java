package com.engelsit.restapp.repository;

import com.engelsit.restapp.entity.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDo, Integer> {
}
