package org.resala.Models.Volunteer;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.resala.Models.Address.Address;
import org.resala.Models.Branch;
import org.resala.Models.Call.CallType;
import org.resala.Models.Event.EventStatus.AttendanceStatus;
import org.resala.Models.KPI.VolunteerKPI;
import org.resala.Models.Privilege.Action;
import org.resala.Models.Privilege.Privilege;
import org.resala.Repository.Volunteer.VolunteerRepo;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Volunteer implements Serializable {
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    Address address;
    @Column(name = "faculty")
    @NotEmpty(message = "Please enter Faculty")
    String faculty;
    @Column(name = "national_id")
    @NotEmpty(message = "Please enter National Id")
    String nationalId;
    @Column(name = "university")
    @NotEmpty(message = "Please enter University")
    String university;
    @Column(name = "region")
    @NotEmpty(message = "Please enter Region")
    String region;
    @Column(name = "first_name")
    @NotEmpty(message = "Please enter First Name")
    String firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "Please enter Last Name")
    String lastName;
    @Column(name = "mid_name")
    @NotEmpty(message = "Please enter Mid Name")
    String midName;
    @Column(name = "nick_name")
    String nickName;
    @Column(name = "phone_number")
    @NotEmpty(message = "Please enter Phone Number")
    String phoneNumber;
    @Column(name = "join_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please Enter Join Date")
    @Temporal(TemporalType.DATE)
    Date joinDate;
    @Column(name = "birth_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Please Enter Birth Date")
    Date birthDate;

    @Column(name = "tShirt")
    @NotNull(message = "T-Shirt Can't be null")
    boolean tShirt;
    @Column(name = "mini_camp")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")//wrong insert
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    //@NotNull(message = "Please Enter MiniCamp DateTime")
    Date miniCamp;//add 2 hours ?!!!!

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "volunteer")
    VolunteerKPI volunteerKPI;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonBackReference
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    //@JsonIdentityReference(alwaysAsId = true)
    public Branch branch;
    @OneToOne(mappedBy = "volunteer", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    //@JsonIdentityReference(alwaysAsId = true)
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    @JoinTable(name = "volunteer_privileges",
            joinColumns = {@JoinColumn(name = "volunteer_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "privilege_id", nullable = false)}
    )
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    //@JsonIdentityReference(alwaysAsId = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Privilege> privileges;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "volunteer",fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<AttendanceStatus> attendanceStatus;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "volunteer_status_id")
    VolunteerStatus volunteerStatus;


    public Volunteer() {
    }


    public boolean equals(Volunteer v) {
        return (this.id == v.getId());
    }
}
