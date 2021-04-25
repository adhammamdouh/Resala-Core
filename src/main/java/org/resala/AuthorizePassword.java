package org.resala;

import org.resala.Models.KPI.VolunteerKPI;
import org.resala.Service.KPI.VolunteerKPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Access;

public class AuthorizePassword {


    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = "test";
        System.out.println(encoder.encode(pass));

    }
}
