package cz.expertkom.sportFacilities.service;

import cz.expertkom.sportFacilities.dto.UserRegisterDto;
import cz.expertkom.sportFacilities.model.User;
import cz.expertkom.sportFacilities.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
//UserMapper slouží pro vytvoření nového objektu a naplnění údaji z FE
public class UserMapper {
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPasswordHash());
        userDto.setRoleId(user.getRoleId().getRoleId());

        return userDto;
    }
    //obraceny princip
    public User toEntity(UserRegisterDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(userDto.getPassword());
        return user;
    }

    //test
    /*public User toEntity2(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(userDto.getPassword());
        return user;
    }*/
}