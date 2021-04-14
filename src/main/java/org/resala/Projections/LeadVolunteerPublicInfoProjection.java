package org.resala.Projections;

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
    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_commit_id")
    Committe committe;*/

    String getGraduationDate();
    int getGraduationNumber();
    boolean getCamp48();
    boolean getGraduated();
    boolean getOmra();
}
