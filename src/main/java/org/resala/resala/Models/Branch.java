package org.resala.resala.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "region")
    String region;
}
