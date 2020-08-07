package se.lexicon.todoitspring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.todoitspring.dto.AppUserDto;
import se.lexicon.todoitspring.dto.TodoItemDto;
import se.lexicon.todoitspring.entity.AppUser;
import se.lexicon.todoitspring.entity.TodoItem;
import se.lexicon.todoitspring.exception.EntityNotFoundException;
import se.lexicon.todoitspring.repository.AppUserRepository;
import se.lexicon.todoitspring.repository.TodoItemRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional(readOnly = true)
public class DtoConversionServiceImpl implements DtoConversionService{


    AppUserRepository appUserRepository;

    @Autowired
    public void setAppUserRepository(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Override
    public AppUser dtoToAppUser(AppUserDto dto) {
        return new AppUser(dto.getId(), dto.getFirstName(), dto.getLastName());
    }

    @Override
    public AppUserDto appUserToDto(AppUser appUser) {
        return new AppUserDto(appUser.getId(), appUser.getFirstName(),appUser.getLastName());
    }

    @Override
    public Collection<AppUserDto> appUserToDto(Collection<AppUser> appUsers) {

        if(appUsers == null) appUsers = new ArrayList<>();
        Collection<AppUserDto> appUserDtos = new ArrayList<>();

        for (AppUser appUser: appUsers) {
            appUserDtos.add(appUserToDto(appUser));
        }

        return appUserDtos;
    }

    @Override
    public TodoItem dtoToTodoItem(TodoItemDto dto) {
        return new TodoItem(dto.getId(), dto.getTitle(), dto.getDescription(), dto.getDeadline(), dto.isDone(), dto.getAssignee().getId() == null ? null : appUserRepository.findById(dto.getAssignee().getId()).orElseThrow( () -> new EntityNotFoundException("Conversion to TodoItem failed: Requested Assignee could not be found")));
    }

    @Override
    public TodoItemDto todoItemToDto(TodoItem todoItem) {
        return new TodoItemDto( todoItem.getId(), todoItem.getTitle(), todoItem.getDescription(), todoItem.getDeadline(), todoItem.isDone(), todoItem.getAssignee());
    }

    @Override
    public Collection<TodoItemDto> todoItemToDto(Collection<TodoItem> todoItems) {

        if (todoItems == null) todoItems = new ArrayList<>();

        Collection<TodoItemDto> todoItemDtos = new ArrayList<>();

        for (TodoItem todoItem: todoItems){
            todoItemDtos.add(todoItemToDto(todoItem));
        }

        return todoItemDtos;
    }
}
