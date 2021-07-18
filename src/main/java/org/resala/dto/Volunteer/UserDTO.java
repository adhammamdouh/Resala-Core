package org.resala.dto.Volunteer;


import lombok.Getter;
import lombok.Setter;
import org.resala.Annotation.Domain.UserName.DomainUserName;
import org.resala.Exceptions.NullException;
import org.resala.dto.Privilege.PrivilegeDTO;

import java.util.ArrayList;

@Getter
@Setter
public class UserDTO {
    private int id;
    @DomainUserName
    private String userName;
    private String password;
    private UserTypeDTO userType;
    private ArrayList<PrivilegeDTO> privileges;

    public  void checkNullForPrivilege(){
        if(privileges==null)
            throw new NullException("Privileges");
    }
}
