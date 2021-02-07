package org.resala.Models.Committe;

import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Volunteer.ResponsibleVolunteer;

import javax.persistence.*;

@Entity
@Table(name = "committee")
@Getter
@Setter
public class Committee {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responsible_volunteer_id")
    ResponsibleVolunteer responsibleVolunteer;
    @Column(name = "committee_name")
    String name;
}
