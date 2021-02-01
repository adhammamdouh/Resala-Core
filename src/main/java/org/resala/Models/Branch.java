package org.resala.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "branch")
@NamedQueries({
        @NamedQuery(name = "Branch.findAll", query = "SELECT w FROM Branch w"),
        @NamedQuery(name = "Branch.findById", query = "SELECT w FROM Branch w WHERE w.id = :id")
})
@Getter
@Setter
public class Branch implements Serializable {
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "region")
    String region;
}
