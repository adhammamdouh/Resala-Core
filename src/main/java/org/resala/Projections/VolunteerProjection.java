package org.resala.Projections;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.resala.Models.Address.Address;
import org.resala.Models.Branch;
import org.resala.Models.Volunteer.VolunteerStatus;

import java.time.LocalDateTime;
import java.util.Date;

public interface VolunteerProjection {
    int getId();

    Address getAddress();

    String getNationalId();

    String getFaculty();

    String getUniversity();

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

    Branch getBranch();

    VolunteerStatus getVolunteerStatus();
}
