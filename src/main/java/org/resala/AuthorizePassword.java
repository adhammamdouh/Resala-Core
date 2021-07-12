package org.resala;

import org.resala.Models.KPI.VolunteerKPI;
import org.resala.Service.KPI.VolunteerKPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Access;

public class AuthorizePassword {


    public static void main(String[] args) {
        for (int i=1;i<39;++i)
            System.out.println("insert into privilege_action (privilege_id,action_id) values (1,"+i+")");

        System.out.println(StaticNames.gender.MALE);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = "test";
        System.out.println(encoder.encode(pass));

    }
}
