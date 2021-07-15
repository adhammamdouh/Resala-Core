package org.resala.Projections.Volunteer;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public interface VolunteerCallsInfoProjection {
    int getId();

    String getFirstName();

    String getLastName();

    String getMidName();

    String getNickName();

    String getPhoneNumber();

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getBirthDate();
}
