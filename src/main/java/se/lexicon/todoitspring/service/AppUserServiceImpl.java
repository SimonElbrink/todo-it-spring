package se.lexicon.todoitspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todoitspring.dto.AppUserDto;
import se.lexicon.todoitspring.entity.AppUser;
import se.lexicon.todoitspring.repository.AppUserRepository;

import java.util.Collection;

@Service
public class AppUserServiceImpl implements AppUserService{

    AppUserRepository appUserRepository;
    DtoConversionService converter;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, DtoConversionService converter) {
        this.appUserRepository = appUserRepository;
        this.converter = converter;
    }

    @Override
    public Collection<AppUserDto> findAll() {
        return converter.appUserToDto((Collection<AppUser>) appUserRepository.findAll());

    }

    @Override
    public AppUserDto findById(String id) {
        return converter.appUserToDto( appUserRepository.findById(id).get());
    }

    @Override
    public Collection<AppUserDto> findByName(String name) {
        return converter.appUserToDto(appUserRepository.findByFirstNameIgnoreCase(name));
    }

    @Override
    public AppUserDto createByForm(AppUserDto appUserDto) {
        return converter.appUserToDto(appUserRepository.save(converter.dtoToAppUser(appUserDto)));
    }

    //TODO
    @Override
    public AppUserDto update(AppUserDto appUserDto) {
        return null;
    }

    //TODO
    @Override
    public void delete(String id) {

    }
}
