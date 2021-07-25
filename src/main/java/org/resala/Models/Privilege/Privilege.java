package org.resala.Models.Privilege;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Organization;
import org.resala.Models.Volunteer.Role;
import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Please Enter Name")
    String name;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Organization organization;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "privilege_Action",
            joinColumns = {@JoinColumn(name = "privilege_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "action_id", nullable = false)}
    )
    private List<Action> actions;


}
