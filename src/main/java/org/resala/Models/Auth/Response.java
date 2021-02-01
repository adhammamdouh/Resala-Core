package org.resala.Models.Auth;


import org.resala.Models.Volunteer.User;
import org.resala.Models.Volunteer.Volunteer;

import java.io.Serializable;

public class Response implements Serializable {

    private final String token;
    private final Volunteer volunteer;

    public Response(String token, Volunteer volunteer) {
        this.token = token;
        this.volunteer=volunteer;
    }

    public String getToken() {
        return token;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }
}
