package org.resala.dto.Volunteer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.resala.Exceptions.NullException;
import org.resala.dto.Address.AddressDTO;
import org.resala.dto.BranchDTO;
import org.resala.dto.OrganizationDTO;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
public class VolunteerDTO {
    int id;
    int gender;
    AddressDTO address;
    BranchDTO branch;
    ShirtDTO shirt;
    UserDTO user;
    RoleDTO role;
    EducationLevelDTO educationLevel;
    OrganizationDTO organization;
    String faculty;
    String nationalId;
    String university;
    String firstName;
    String lastName;
    String midName;
    String nickName;
    String phoneNumber;
    String comments;
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date joinDate;
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date birthDate;
    boolean miniCamp;

    public void checkNull() {
        if (address == null) {
            throw new NullException("Address");
        }
        if (branch == null) {
            throw new NullException("Branch");
        }
        if (shirt == null) {
            throw new NullException("T-Shirt");
        }
        address.checkNull();
    }

}
