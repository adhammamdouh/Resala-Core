package org.resala.Projections.LeadVolunteer;

import org.resala.Models.Committee.Committee;
import org.resala.Projections.Volunteer.VolunteerPublicInfoProjection;

import java.util.Date;

public interface LeadVolunteerPublicInfoProjection {
    int getId();
    VolunteerPublicInfoProjection getMyVolunteerInfo();
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
    Date getOmra();
}
