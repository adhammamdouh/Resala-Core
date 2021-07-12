package org.resala.dto.Volunteer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.resala.Exceptions.NullException;
import org.resala.dto.Address.AddressDTO;
import org.resala.dto.BranchDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class VolunteerDTO {
    int id;
    int gender;
    AddressDTO address;
    BranchDTO branch;
    String faculty;
    String nationalId;
    String university;
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

    public void checkNull() {
        if (address == null) {
            throw new NullException("Address");
        }
        if (branch == null) {
            throw new NullException("Branch");
        }
        address.checkNull();
    }

}
