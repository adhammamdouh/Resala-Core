package org.resala.Projections;


import org.resala.Models.Privilege.Privilege;

import java.util.List;

public interface testName {
    //@Value("#{target.firstName}")
    String getFirstName();
    //@Value("#{target.lastName}")
    String getLastName();
    List<Privilege> getPrivileges();
}
