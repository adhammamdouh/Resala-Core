package org.resala.Models.Volunteer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Models.Committe.Committee;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "lead_volunteer")
@Getter
@Setter
public class LeadVolunteer implements Serializable {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "volunteer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Volunteer myVolunteerInfo;
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
    boolean doctorMeeting;
    @Column(name = "graduation_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date graduationDate;
    @Column(name = "graduation_number")
    int graduationNumber;
    @Column(name = "camp_48")
    int camp48;
    @Column(name = "graduated")
    boolean graduated;
    @Column(name = "omra")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date omra;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "committe_id")
    Committee committee;
}
