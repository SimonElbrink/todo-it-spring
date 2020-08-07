package se.lexicon.todoitspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import se.lexicon.todoitspring.dto.TodoItemDto;
import se.lexicon.todoitspring.service.TodoItemService;

import java.util.Collection;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/todoItem")
public class TodoItemController {

    public static final String ALL = "ALL";
    public static final String ID = "ID";
    private TodoItemService service;

    @Autowired
    public TodoItemController(TodoItemService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItemDto> findById(@PathVariable String id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> find(
            @RequestParam(name = "type", defaultValue = ALL) final String type,
            @RequestParam(name = "value", defaultValue = ALL) final String value
    ){

        switch (type.toUpperCase().trim()){

            case ID:
                return ResponseEntity.ok(service.findById(value));

            case ALL:
                Collection<TodoItemDto> found = service.findAll();
                return found.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(found);

            default: return ResponseEntity.badRequest().body("Not a valid type: " + type);
        }
    }

    @PostMapping
    public ResponseEntity<TodoItemDto> createByForm(@RequestBody TodoItemDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createByForm(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoItemDto> updateByForm(@PathVariable String id, @RequestBody TodoItemDto updated){

        if (id != updated.getId()){
            throw new IllegalArgumentException("ID's need to match");
        }

        return ResponseEntity.ok(service.update(updated));
    }


}
