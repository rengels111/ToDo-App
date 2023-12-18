package com.engelsit.restapp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ToDoController {

    @GetMapping("/todo")
    public ResponseEntity<ToDo> get(@RequestParam(value = "id") int id) {

        ToDo newToDo = new ToDo();
        newToDo.setId(id);
        newToDo.setDescription("Einkaufen");
        newToDo.setIsDone(true);

        return new ResponseEntity<ToDo>(newToDo, HttpStatus.OK);
    }

    @PostMapping("/todo")
    public ResponseEntity<ToDo> create(@RequestBody ToDo newToDo) {
        // save todo in db
        return new ResponseEntity<ToDo>(newToDo, HttpStatus.OK);
    }
}


