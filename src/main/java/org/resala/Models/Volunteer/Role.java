package org.resala.Models.Volunteer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role implements Serializable {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Please Enter Role Name")
    String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "role")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<Volunteer>volunteers;
}
