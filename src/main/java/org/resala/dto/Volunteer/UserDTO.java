package org.resala.dto.Volunteer;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int id;
    private String userName;
    private String password;
    private UserTypeDTO userType;

}
