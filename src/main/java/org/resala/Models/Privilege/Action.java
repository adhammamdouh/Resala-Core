package org.resala.Models.Privilege;

import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Action {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "name")
    String name;

    @ManyToMany(mappedBy = "privileges", cascade = CascadeType.ALL)
    private List<Volunteer> actions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
