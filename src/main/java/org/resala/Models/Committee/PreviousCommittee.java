package org.resala.Models.Committee;

import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Volunteer.LeadVolunteer;
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
    LeadVolunteer leadVolunteer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "role_id")
    Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "committee_id")
    Committee committee;

}
