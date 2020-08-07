package se.lexicon.todoitspring.service;

import se.lexicon.todoitspring.dto.AppUserDto;

import java.util.Collection;

public interface AppUserService {

    Collection<AppUserDto> findAll();
    AppUserDto findById(String id);
    Collection<AppUserDto> findByName(String name);

    AppUserDto createByForm(AppUserDto appUserDto);
    AppUserDto update(AppUserDto appUserDto);

    void delete(String id);

}
