package se.lexicon.todoitspring.service;

import se.lexicon.todoitspring.dto.AppUserDto;
import se.lexicon.todoitspring.dto.TodoItemDto;
import se.lexicon.todoitspring.entity.AppUser;
import se.lexicon.todoitspring.entity.TodoItem;

import java.util.Collection;

public interface DtoConversionService {

    AppUser dtoToAppUser(AppUserDto dto);
    AppUserDto appUserToDto(AppUser appUser);
    Collection<AppUserDto> appUserToDto(Collection<AppUser> appUsers);

    TodoItem dtoToTodoItem(TodoItemDto dto);
    TodoItemDto todoItemToDto(TodoItem todoItem);
    Collection<TodoItemDto>todoItemToDto(Collection<TodoItem> todoItems);



}
