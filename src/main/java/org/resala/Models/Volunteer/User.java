package org.resala.Models.Volunteer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.resala.Annotation.Domain.UserName.DomainUserName;
import org.resala.Models.Privilege.Privilege;

import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name", nullable = false,unique = true)
    @NotEmpty(message = "Please enter UserName")
    @DomainUserName
    private String userName;
    @Column(nullable = false)
    @NotEmpty(message = "Please enter Password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_type_id", nullable = false)
    @NotNull(message = "User Type Can't be null")
    UserType userType;

    /*@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id", nullable = false)
    @NotNull(message = "Organization Can't be null")
    Organization organization;*/


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_privileges",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "privilege_id", nullable = false)}
    )
    private List<Privilege> privileges;

   /* @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonBackReference
    Volunteer volunteer;

    @OneToOne(targetEntity = Cloud.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonBackReference
    Cloud cloud;

    @OneToOne(targetEntity = Admin.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonBackReference
    Admin admin;*/
}
