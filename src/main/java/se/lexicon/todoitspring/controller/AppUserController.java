package se.lexicon.todoitspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todoitspring.dto.AppUserDto;
import se.lexicon.todoitspring.service.AppUserService;

import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/appUser")
public class AppUserController {

    public static final String ALL = "ALL";
    AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public ResponseEntity<?> find(
            @RequestParam(name = "type", defaultValue = ALL) final String type,
            @RequestParam(name = "value", defaultValue = ALL) final String value
    ){

        switch (type.toUpperCase().trim()){
            case ALL:
                Collection<AppUserDto> found = appUserService.findAll();
                return found.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(found);

            default: return ResponseEntity.badRequest().body("Not a valid type: " + type);
        }
    }
}
