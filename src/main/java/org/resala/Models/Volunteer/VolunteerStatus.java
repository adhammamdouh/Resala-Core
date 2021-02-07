package org.resala.Models.Volunteer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "volunteer_status")
@Getter
@Setter
public class VolunteerStatus {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name",nullable = false)
    String name;
}
