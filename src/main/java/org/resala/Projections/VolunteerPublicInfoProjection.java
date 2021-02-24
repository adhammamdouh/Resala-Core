package org.resala.Projections;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.resala.Models.Address.Address;
import org.resala.Models.Privilege.Privilege;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface VolunteerPublicInfoProjection {
    int getId();
    VolunteerPublicAddressInfoProjection getAddress();
    String getFaculty();
    String getUniversity();
    String getRegion();
    //@Value("#{target.firstName}")
    String getFirstName();
    //@Value("#{target.lastName}")
    String getLastName();
    String getMidName();
    String getNickName();
    String getPhoneNumber();
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getJoinDate();
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getBirthDate();
    boolean gettShirt();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime getMiniCamp();
}
