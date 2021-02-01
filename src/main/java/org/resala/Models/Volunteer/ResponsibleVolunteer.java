package org.resala.Models.Volunteer;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Exceptions.Committe.Committe;

import javax.persistence.*;

@Entity
@Table(name = "responsible_volunteer")
public class ResponsibleVolunteer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "personal_image_url")
    String personalImageUrl;
    @Column(name = "resala_objective")
    String resalaObjective;
    @Column(name = "personal_objective")
    String personalObjective;
    @Column(name = "self_skills")
    String selfSkills;
    @Column(name = "dreams")
    String dreams;
    @Column(name = "national_id_url")
    String nationalIdUrl;
    @Column(name = "doctor_meeting")
    String doctorMeeting;
    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_commit_id")
    Committe committe;*/
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "volunteer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Volunteer volunteer;
    @Column(name = "graduation_date")
    String GraduationDate;
    @Column(name = "graduation_number")
    int GraduationNumber;
    @Column(name = "camp_48")
    boolean camp48;
    @Column(name = "graduated")
    boolean graduated;
    @Column(name = "omra")
    boolean omra;
}
