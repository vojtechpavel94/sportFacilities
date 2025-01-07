package cz.expertkom.sportFacilities.service;

import cz.expertkom.sportFacilities.dto.FacilityDto;
import cz.expertkom.sportFacilities.model.Facility;
import cz.expertkom.sportFacilities.model.UserRole;
import cz.expertkom.sportFacilities.model.User;
import cz.expertkom.sportFacilities.dto.UserDto;
import cz.expertkom.sportFacilities.dto.UserRegisterDto;
import cz.expertkom.sportFacilities.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserRoleService userRoleService;


    public UserService (UserRepository userRepository, UserMapper userMapper, UserRoleService roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userRoleService = roleRepository;
    }

    public List<UserDto> getAll() {
        /*List<UserDto> list = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            UserDto dto = userMapper.toDto(user);
            list.add(dto);
        }*/
        log.info("#US&ga01: getAll called");
        //return list;
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    public User registerUser (UserRegisterDto userRegisterDto){
        log.info("#US&ru01: registerUser called, userRegisterDto={}", userRegisterDto);
        User user = new User();
        user.setPasswordHash(userRegisterDto.getPassword());
        user.setEmail(userRegisterDto.getEmail());
        user.setUsername(userRegisterDto.getUsername());

        UserRole rs = userRoleService.getById(userRegisterDto.getRoleId());
        user.setRoleId(rs);
        user.setCreatedAt(new Date());


        return userRepository.save(user);
    }

    //TODO - Update user - Error: NG04008
    public UserDto updateUser(int id, UserRegisterDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setUserId(id); // původní ID dáme znovu do UserID
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    //TODO - Delete user - undefined je problém

    public void deleteUserById(Integer id) {
        log.info("#US&du01: deleteUserById called, id={}", id);

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        if (!userRepository.existsById(id)) {
            log.warn("#US&du02: User with id={} does not exist", id);
            throw new IllegalArgumentException("User with id " + id + " does not exist");
        }

        userRepository.deleteById(id);
    }
}