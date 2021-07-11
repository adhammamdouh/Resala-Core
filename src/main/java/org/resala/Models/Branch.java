package org.resala.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Event.Event;
import org.resala.Models.Event.EventResult;
import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "branch")
@Getter
@Setter
public class Branch implements Serializable {
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    @NotEmpty(message = "Please Enter Branch Name")
    String name;
    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Volunteer> volunteers;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<EventResult> eventResults;
}
