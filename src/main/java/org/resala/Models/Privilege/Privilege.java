package org.resala.Models.Privilege;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Volunteer.Role;
import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "privilege")
@Getter
@Setter
public class Privilege {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "privilege_Action",
            joinColumns = {@JoinColumn(name = "privilege_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "action_id", nullable = false)}
    )
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Action> actions;
}
