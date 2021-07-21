package org.resala.Projections.Volunteer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Address.Address;
import org.resala.Models.Branch;
import org.resala.Models.KPI.VolunteerKPI;
import org.resala.Models.Volunteer.EducationLevel;
import org.resala.Models.Volunteer.Shirt;
import org.resala.Models.Volunteer.UserStatus;

import java.util.Date;

public interface VolunteerProjection {
    int getId();

    int getGender();

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

    int getAge();

    Shirt getShirt();

    boolean getMiniCamp();

    VolunteerKPI getVolunteerKPI();

    Branch getBranch();

    UserStatus getVolunteerStatus();

    EducationLevel getEducationLevel();
}
