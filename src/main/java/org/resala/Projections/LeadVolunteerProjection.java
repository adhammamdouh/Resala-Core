package org.resala.Projections;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_commit_id")
    Committe committe;*/

    String getGraduationDate();
    int getGraduationNumber();
    boolean getCamp48();
    boolean getGraduated();
    boolean getOmra();
}
