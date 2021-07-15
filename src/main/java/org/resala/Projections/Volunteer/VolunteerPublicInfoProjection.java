package org.resala.Projections.Volunteer;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.resala.Models.Branch;
import org.resala.Models.KPI.VolunteerKPI;
import org.resala.Models.Volunteer.UserStatus;

import java.util.Date;

public interface VolunteerPublicInfoProjection {
    int getId();

    VolunteerPublicAddressInfoProjection getAddress();

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

    int gettShirt();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    boolean getMiniCamp();

    VolunteerKPI getVolunteerKPI();

    Branch getBranch();

    UserStatus getVolunteerStatus();
}
