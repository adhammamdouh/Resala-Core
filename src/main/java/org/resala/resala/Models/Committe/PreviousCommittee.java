package org.resala.resala.Models.Committe;

import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Committe.Committee;
import org.resala.Models.Volunteer.ResponsibleVolunteer;
import org.resala.Models.Volunteer.Role;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class PreviousCommittee {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
