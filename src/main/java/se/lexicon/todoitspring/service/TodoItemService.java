package se.lexicon.todoitspring.service;



import se.lexicon.todoitspring.dto.TodoItemDto;


import java.time.LocalDateTime;
import java.util.Collection;

public interface TodoItemService {

    Collection<TodoItemDto> findAll();
    TodoItemDto findById(String id);
    Collection<TodoItemDto> searchByTitle(String title);
    Collection<TodoItemDto> searchByDoneStatus(boolean doneStatus);
    Collection<TodoItemDto> searchByDateBefore(LocalDateTime end);

    TodoItemDto createByForm(TodoItemDto todoItemDto);
    TodoItemDto update(TodoItemDto todoItemDto);

    void delete(String id);

}
