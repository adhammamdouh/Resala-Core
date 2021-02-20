package org.resala.dto.Volunteer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.resala.dto.Address.AddressDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
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
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date joinDate;
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date birthDate;
    boolean tShirt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime miniCamp;


}
