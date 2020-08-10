package se.lexicon.todoitspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todoitspring.dto.TodoItemDto;
import se.lexicon.todoitspring.dto.TodoItemForm;
import se.lexicon.todoitspring.entity.TodoItem;
import se.lexicon.todoitspring.exception.EntityNotFoundException;
import se.lexicon.todoitspring.repository.AppUserRepository;
import se.lexicon.todoitspring.repository.TodoItemRepository;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class TodoItemServiceImpl implements TodoItemService {

    TodoItemRepository todoItemRepository;
    DtoConversionService converter;
    AppUserRepository appUserRepository;

    @Autowired
    public TodoItemServiceImpl(TodoItemRepository todoItemRepository, DtoConversionService converter, AppUserRepository appUserRepository) {
        this.todoItemRepository = todoItemRepository;
        this.converter = converter;
        this.appUserRepository = appUserRepository;
    }


    @Override
    public Collection<TodoItemDto> findAll() {
        return converter.todoItemToDto( (Collection<TodoItem>) todoItemRepository.findAll());
    }

    @Override
    public TodoItemDto findById(String id) {
        return converter.todoItemToDto(todoItemRepository.findById(id).get());
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
    public TodoItemDto createByForm(TodoItemForm todoItemForm) {

        if (todoItemForm.getId() != null){
            throw new IllegalArgumentException("Invalid TodoItem ID: ID should not be specified at creation.");
        }
        return converter.todoItemToDto(todoItemRepository.save(converter.TodoItemFormToTodoItem(todoItemForm)));
    }

    @Override
    public TodoItemDto update(TodoItemForm todoItemForm) {
        if (todoItemForm.getId() == null){
            throw new IllegalArgumentException("TodoItem had a Invalid ID: " + todoItemForm.getId());
        }

        TodoItem todoItem = todoItemRepository.findById(todoItemForm.getId()).orElseThrow(() -> new EntityNotFoundException("Could not find TodoItem with ID: " + todoItemForm.getId()));


        TodoItem updated = converter.TodoItemFormToTodoItem(todoItemForm);

        todoItem.setTitle(updated.getTitle());
        todoItem.setDescription(updated.getDescription());
        todoItem.setDeadline(updated.getDeadline());
        todoItem.setDone(updated.isDone());

        todoItem.setAssignee(null);
        todoItem.setAssignee(updated.getAssignee());

        return converter.todoItemToDto(todoItemRepository.save(todoItem));

    }

    @Override
    public void delete(String id) {

    }
}
