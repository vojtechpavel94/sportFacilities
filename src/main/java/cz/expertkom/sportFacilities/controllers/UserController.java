package cz.expertkom.sportFacilities.controllers;
import cz.expertkom.sportFacilities.dto.UserDto;
import cz.expertkom.sportFacilities.model.User;
import cz.expertkom.sportFacilities.dto.UserRegisterDto;
import cz.expertkom.sportFacilities.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController //provolávání jen z PostMana
@RequestMapping("/users") //možná lepší mít /api/v1/users
@Slf4j
public class UserController {
    //využití mezi vrstvy v podobě Service - slouží pro naplnění objektu daty z FE o uživateli
    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping //využití speciálního objektu Dto pro komunikaci s FrontEndem //Data Transfer Object
    //používá se to kvůli tomu, že FE posílá mnohem méně údaju z databáze, neposílá ID, created at, update at atd.
    public User registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        log.info("#UC&ru01: registerUser called");
        return userService.registerUser(userRegisterDto);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info("#UC&gu01: getAllUsers called");
        return userService.getAll();
    }
}