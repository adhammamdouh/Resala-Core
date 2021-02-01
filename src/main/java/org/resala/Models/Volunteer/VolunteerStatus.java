package org.resala.Models.Volunteer;

import javax.persistence.*;

@Entity
@Table(name = "volunteer_status")
public class VolunteerStatus {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "name",nullable = false)
    String name;
}
