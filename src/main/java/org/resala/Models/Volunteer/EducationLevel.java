package org.resala.Models.Volunteer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "education_level")
@Getter
@Setter
public class EducationLevel implements Serializable {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "education_level_name")
    String name;

}
