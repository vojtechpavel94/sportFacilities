package cz.expertkom.sportFacilities.service;

import cz.expertkom.sportFacilities.model.UserRole;
import cz.expertkom.sportFacilities.model.User;
import cz.expertkom.sportFacilities.dto.UserDto;
import cz.expertkom.sportFacilities.dto.UserRegisterDto;
import cz.expertkom.sportFacilities.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
        user.setPassword(userRegisterDto.getPassword());
        user.setEmail(userRegisterDto.getEmail());
        user.setUsername(userRegisterDto.getUsername());

        UserRole rs = userRoleService.getById(userRegisterDto.getRoleId());
        user.setRoleId(rs);
        user.setCreatedAt(new Date());


        return userRepository.save(user);

        //User userEntity = userMapper.toEntity(userRegisterDto);
        //return userRepository.save(userEntity);
    }

}
