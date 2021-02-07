package org.resala.Models.Privilege;

import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Action {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;
}
