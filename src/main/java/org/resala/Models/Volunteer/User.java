package org.resala.Models.Volunteer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Organization;

import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
public class User implements Serializable {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "volunteer_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    Volunteer volunteer;
}
