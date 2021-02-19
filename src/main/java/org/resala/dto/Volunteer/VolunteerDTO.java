package org.resala.dto.Volunteer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.resala.dto.Address.AddressDTO;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

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
    //@JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(value = TemporalType.DATE)
    Date joinDate;
   // @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(value = TemporalType.DATE)
    Date birthDate;
    boolean tShirt;
    @Temporal(value = TemporalType.TIMESTAMP)
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date miniCamp;


}
