package org.resala.Models.Committe;

import org.resala.Models.Volunteer.ResponsibleVolunteer;

import javax.persistence.*;

@Entity
@Table(name = "committee")
public class Committee {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responsible_volunteer_id")
    ResponsibleVolunteer responsibleVolunteer;
    @Column(name = "committee_name")
    String name;
}
