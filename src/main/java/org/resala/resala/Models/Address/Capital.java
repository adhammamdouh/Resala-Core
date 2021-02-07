package org.resala.resala.Models.Address;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "capital")
@Getter
@Setter
public class Capital {
    @Column(name = "capital_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;
}
