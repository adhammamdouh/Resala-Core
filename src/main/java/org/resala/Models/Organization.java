package org.resala.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.User;
import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "organization")
@Getter
@Setter
public class Organization {
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    @NotEmpty(message = "Please Enter Organization Name")
    String name;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonBackReference
    private List<Branch> branches;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonBackReference
    private List<Volunteer> volunteers;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonBackReference
    private List<Event> events;
}
