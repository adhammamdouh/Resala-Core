package org.resala.Models.Volunteer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Shirt {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    @NotEmpty(message = "Please enter name")
    String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shirt")
    @JsonBackReference
    List<Volunteer> volunteers;
}
