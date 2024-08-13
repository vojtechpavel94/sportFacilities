package cz.expertkom.sportFacilities.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String email;
    private String username;
    private String password;
    private int roleId;
}
