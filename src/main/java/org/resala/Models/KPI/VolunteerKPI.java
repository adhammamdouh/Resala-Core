package org.resala.Models.KPI;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
    @Column(name = "calls_count", nullable = false)
    int callsCount;
    @Column(name = "present_count", nullable = false)
    int presentCount;
    @Column(name = "ensure_count",nullable = false)
    int ensureCount;
    @Column(name = "response_count", nullable = false)
    int responseCount;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "volunteer_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    Volunteer volunteer;
}
