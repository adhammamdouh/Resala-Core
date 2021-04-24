package org.resala.Models.Volunteer;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.resala.Annotation.PhoneNumber.Phone;
import org.resala.Models.Address.Address;
import org.resala.Models.Branch;
import org.resala.Models.Call.Calls;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Event.Attendance.EventAttendance;
import org.resala.Models.KPI.VolunteerKPI;
import org.resala.Models.Privilege.Privilege;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
/*@NamedQueries({
        //@NamedQuery(name = "Volunteer.findById", query = "SELECT w FROM Volunteer w WHERE w.id = :id"),
        @NamedQuery(name = "Volunteer.findByBranch", query = "SELECT v FROM Volunteer v WHERE v.branch.id = :branch_id")
})*/
public class Volunteer implements Serializable {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
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
    @Phone
    String phoneNumber;
    @Column(name = "join_date")
    //@JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please Enter Join Date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date joinDate;
    @Column(name = "birth_date")
    //@JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Please Enter Birth Date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date birthDate;

    @Column(name = "tShirt")
    @NotNull(message = "T-Shirt Can't be null")
    boolean tShirt;
    @Column(name = "mini_camp")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime miniCamp;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "volunteer")
    VolunteerKPI volunteerKPI;

    @OneToOne(mappedBy = "volunteer", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Branch branch;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "network_type_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public NetworkType networkType;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    //@JsonIdentityReference(alwaysAsId = true)
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    @JoinTable(name = "volunteer_privileges",
            joinColumns = {@JoinColumn(name = "volunteer_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "privilege_id", nullable = false)}
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    //@JsonIdentityReference(alwaysAsId = true)

    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Privilege> privileges;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "volunteer", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<EventAttendance> eventAttendances;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "volunteer_status_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    VolunteerStatus volunteerStatus;

    @OneToOne(mappedBy = "myVolunteerInfo",fetch = FetchType.EAGER)
    @JsonBackReference
    LeadVolunteer leadVolunteer;
    public Volunteer() {
    }


    public boolean equals(Volunteer v) {
        return (this.id == v.getId());
    }
}
