package org.resala.Projections;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.resala.Models.Address.Address;
import org.resala.Models.Privilege.Privilege;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public interface VolunteerPublicInfoProjection {
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
    Date getJoinDate();
    Date getBirthDate();
    boolean gettShirt();
    Date getMiniCamp();
}
