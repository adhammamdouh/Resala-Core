package org.resala.Models.Volunteer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "volunteer_status")
@Getter
@Setter
public class VolunteerStatus {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Please Enter Volunteer Status Name")
    String name;
    @OneToMany(mappedBy = "volunteerStatus",fetch = FetchType.LAZY)
    @JsonBackReference
    List<Volunteer> volunteers;
}
