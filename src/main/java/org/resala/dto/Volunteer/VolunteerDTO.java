package org.resala.dto.Volunteer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.resala.dto.Address.AddressDTO;

@Getter
@Setter
public class VolunteerDTO {
    int id;
    int age;
    AddressDTO address;
    int branchId;
    String faculty;
    String nationalId;
    String university;
    String region;
    String firstName;
    String lastName;
    String midName;
    String nickName;
    String phoneNumber;
    String joinDate;
    String birthDate;
    boolean tShirt;
    boolean miniCamp;


}
