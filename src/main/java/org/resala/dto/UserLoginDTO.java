package org.resala.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserLoginDTO implements Serializable {
    private int organizationId;
    private String username;
    private String password;
}
