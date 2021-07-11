package org.resala.Projections.Volunteer;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public interface VolunteerCallsInfoProjection {
    String getLastName();

    String getMidName();

    String getNickName();

    String getPhoneNumber();

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getBirthDate();
}
