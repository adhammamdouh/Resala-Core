package org.resala.Models.Committe;

import org.resala.Models.Volunteer.ResponsibleVolunteer;
import org.resala.Models.Volunteer.Role;

import javax.persistence.*;

@Entity
@Table
public class PreviousCommittee {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "responsible_volunteer_id")
    ResponsibleVolunteer responsibleVolunteer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "role_id")
    Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "committee_id")
    Committee committee;

}
