package se.lexicon.todoitspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todoitspring.dto.TodoItemDto;
import se.lexicon.todoitspring.entity.TodoItem;
import se.lexicon.todoitspring.exception.EntityNotFoundException;
import se.lexicon.todoitspring.repository.TodoItemRepository;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class TodoItemServiceImpl implements TodoItemService {

    TodoItemRepository repository;
    DtoConversionService converter;

    @Autowired
    public TodoItemServiceImpl(TodoItemRepository repository, DtoConversionService converter) {
        this.repository = repository;
        this.converter = converter;
    }


    @Override
    public Collection<TodoItemDto> findAll() {
        return converter.todoItemToDto( (Collection<TodoItem>) repository.findAll());
    }

    @Override
    public TodoItemDto findById(String id) {
        return converter.todoItemToDto(repository.findById(id).get());
    }

    @Override
    public Collection<TodoItemDto> searchByTitle(String title) {
        return null;
    }

    @Override
    public Collection<TodoItemDto> searchByDoneStatus(boolean doneStatus) {
        return null;
    }

    @Override
    public Collection<TodoItemDto> searchByDateBefore(LocalDateTime end) {
        return null;
    }

    @Override
    public TodoItemDto createByForm(TodoItemDto todoItemDto) {

        if (todoItemDto.getId() != null){
            throw new IllegalArgumentException("Invalid TodoItem ID: ID should not be specified at creation.");
        }
        return converter.todoItemToDto(repository.save(converter.dtoToTodoItem(todoItemDto)));
    }

    @Override
    public TodoItemDto update(TodoItemDto todoItemDto) {
        if (todoItemDto.getId() == null){
            throw new IllegalArgumentException("TodoItem had a Invalid ID: " + todoItemDto.getId());
        }

        TodoItem todoItem = repository.findById(todoItemDto.getId()).orElseThrow(() -> new EntityNotFoundException("Could not find TodoItem with ID: " + todoItemDto.getId()));

        TodoItem updated = converter.dtoToTodoItem(todoItemDto);

        todoItem.setTitle(updated.getTitle());
        todoItem.setDescription(updated.getDescription());
        todoItem.setDeadline(updated.getDeadline());
        todoItem.setDone(updated.isDone());

        todoItem.setAssignee(null);
        todoItem.setAssignee(updated.getAssignee());

        return converter.todoItemToDto(repository.save(todoItem));

    }

    @Override
    public void delete(String id) {

    }
}
