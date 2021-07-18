package org.resala.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserLoginDTO implements Serializable {
    private String userName;
    private String password;
}
