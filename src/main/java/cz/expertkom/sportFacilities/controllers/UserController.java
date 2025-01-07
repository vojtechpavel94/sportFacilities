package cz.expertkom.sportFacilities.controllers;
import cz.expertkom.sportFacilities.dto.FacilityDto;
import cz.expertkom.sportFacilities.dto.UserDto;
import cz.expertkom.sportFacilities.model.User;
import cz.expertkom.sportFacilities.dto.UserRegisterDto;
import cz.expertkom.sportFacilities.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController //provolávání jen z PostMana
@RequestMapping("/api/v1/users") //možná lepší mít /api/v1/users
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

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable int id, @RequestBody UserRegisterDto userDto) {
        log.info("User with ID: {}", id);
        UserDto updatedUser = userService.updateUser(id, userDto); // Použití instance userService
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteUser(@PathVariable String id) {
            log.info("#UC&du01: deleteUser called, id={}", id);

            //Testování undefined problému
            try {
                int userId = Integer.parseInt(id);
                log.info("#UC&du01: userID called, id={}", id);
                userService.deleteUserById(userId);
                return ResponseEntity.noContent().build();
            } catch (NumberFormatException ex) {
                    log.error("#UC&du02: Invalid ID format: {}", id);
                    return ResponseEntity.badRequest().body(Map.of(
                            "error", "Invalid user ID format",
                            "providedValue", id,
                            "expectedFormat", "Integer"
                    ));

            } catch (IllegalArgumentException ex) {
                log.error("#UC&du03: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
            }
        }
    }
