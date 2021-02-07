package org.resala.resala.Models.KPI;

import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.*;

@Entity
@Table(name = "volunteer_KPI")
@Getter
@Setter
public class VolunteerKPI {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "calls_count",nullable = false)
    int callsCount;
    @Column(name = "present_count",nullable = false)
    int presentCount;
    @Column(name = "ensure_count",nullable = false)
    int ensureCount;
    @Column(name = "response_count",nullable = false)
    int responseCount;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "volunteer_id",nullable = false)
    Volunteer volunteer;
}
