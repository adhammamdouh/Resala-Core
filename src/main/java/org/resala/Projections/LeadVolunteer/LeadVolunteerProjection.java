package org.resala.Projections.LeadVolunteer;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.resala.Models.Committee.Committee;
import org.resala.Models.KPI.LeadVolunteerKPI;
import org.resala.Projections.Volunteer.VolunteerProjection;

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
    Committee getCommittee();
    String getGraduationDate();
    int getGraduationNumber();
    int getCamp48();
    boolean getGraduated();
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getOmra();
    LeadVolunteerKPI getLeadVolunteerKPI();
}
