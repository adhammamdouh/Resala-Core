package org.resala.Projections;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Models.Committe.Committee;
import org.resala.Models.KPI.LeadVolunteerKPI;
import org.resala.Models.Volunteer.LeadVolunteer;
import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.*;
import java.util.Date;
//@Projection(name = "leadVolunteerProjection", types = { LeadVolunteer.class })
//@EntityView(LeadVolunteer.class)
public interface LeadVolunteerProjection {
    int getId();
    VolunteerProjection getMyVolunteerInfo();
    String getPersonalImageUrl();
    String getResalaObjective();
    String getPersonalObjective();
    String getSelfSkills();
    String getDreams();
    String getNationalIdUrl();
    String getDoctorMeeting();

    String getGraduationDate();
    int getGraduationNumber();
    int getCamp48();
    boolean getGraduated();
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getOmra();
    LeadVolunteerKPI getLeadVolunteerKPI();
}
