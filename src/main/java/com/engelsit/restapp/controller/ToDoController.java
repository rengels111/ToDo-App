package com.engelsit.restapp.controller;

import com.engelsit.restapp.entity.ToDo;
import com.engelsit.restapp.repository.ToDoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ToDoController {

    private final ToDoRepository toDoRepository;

    public ToDoController(ToDoRepository toDoRepository) {  // Constructor Injection better than Field Injection.
        this.toDoRepository = toDoRepository;
    }

    @GetMapping("/todo")
    public ResponseEntity<ToDo> get(@RequestParam(value = "id") int id) {
        Optional<ToDo> toDoInDb = toDoRepository.findById(id);

        // get ToDo from db by id
        if (toDoInDb.isPresent()) {
            return new ResponseEntity<>(toDoInDb.get(), HttpStatus.OK);
        }
        return new ResponseEntity("No ToDo found with id " +id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/todo/all")
    public ResponseEntity<Iterable<ToDo>> getAll() {
        Iterable<ToDo> allToDos = toDoRepository.findAll();

        return new ResponseEntity<>(allToDos, HttpStatus.OK);
    }

    @PostMapping("/todo")
    public ResponseEntity<ToDo> create(@RequestBody ToDo newToDo) {  // RequestBody instead of Param: information via
        // JSON in body, so we can place more individual information than as parameter.
        // save ToDo in db
        toDoRepository.save(newToDo);
        return new ResponseEntity<>(newToDo, HttpStatus.CREATED);
    }

    @DeleteMapping("/todo")
    public ResponseEntity delete(@RequestParam(value = "id") int id) {
        Optional<ToDo> toDoInDb = toDoRepository.findById(id);

        if (toDoInDb.isPresent()) {
            toDoRepository.deleteById(id);
            return new ResponseEntity("ToDo deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity("No ToDo found with id " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/todo")  // Put exchanges to whole ToDo. Patch would update only a part of the ToDo.
    public ResponseEntity<ToDo> edit(@RequestBody ToDo editToDo) {
        Optional<ToDo> toDoInDb = toDoRepository.findById(editToDo.getId());

        if (toDoInDb.isPresent()) {
            ToDo editedToDo = toDoRepository.save(editToDo);
            return new ResponseEntity<>(editedToDo, HttpStatus.OK);
        }
        return new ResponseEntity("No suitable ToDo found with id " + editToDo.getId(), HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/todo/setDone")  // Patch to update the "isDone" parameter.
    public ResponseEntity<ToDo> setIsDone(@RequestParam(value = "isDone") boolean isDone,
                                          @RequestParam(value = "id") int id) {
        Optional<ToDo> toDoInDb = toDoRepository.findById(id);
        if (toDoInDb.isPresent()) {
            toDoInDb.get().setIsDone(isDone);
            ToDo savedToDo = toDoRepository.save(toDoInDb.get());
            return new ResponseEntity<>(savedToDo, HttpStatus.OK);
        }
        return new ResponseEntity("No suitable ToDo found with id " + id, HttpStatus.NOT_FOUND);
    }
}


