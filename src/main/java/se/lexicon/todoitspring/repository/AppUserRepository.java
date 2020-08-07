package se.lexicon.todoitspring.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.todoitspring.entity.AppUser;

import java.util.Collection;


public interface AppUserRepository extends CrudRepository<AppUser, String> {

    Collection<AppUser> findByFirstNameIgnoreCase(String firstName);

}
