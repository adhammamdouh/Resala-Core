package org.resala.Models.KPI;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Models.Volunteer.ResponsibleVolunteer;

import javax.persistence.*;

@Entity
@Table(name = "responsible_volunteer_KPI")
@Getter
@Setter
public class ResponsibleVolunteerKPI {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responsible_volunteer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    ResponsibleVolunteer responsibleVolunteer;
    @Column(name = "present_count")
    int presentCount;
    @Column(name = "ensure_count")
    int ensureCount;
    @Column(name = "calls_count")
    int callsCount;

}
