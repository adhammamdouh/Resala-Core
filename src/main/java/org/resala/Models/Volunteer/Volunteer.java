package org.resala.Models.Volunteer;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Models.Address.Address;
import org.resala.Models.Branch;
import org.resala.Models.Call.CallType;
import org.resala.Models.Event.EventStatus.AttendanceStatus;
import org.resala.Models.KPI.VolunteerKPI;
import org.resala.Models.Privilege.Action;
import org.resala.Models.Privilege.Privilege;
import org.resala.Repository.Volunteer.VolunteerRepo;

import javax.persistence.*;
import java.io.Serializable;
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
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    Address address;

    @Column(name = "age", nullable = false)
    int age;
    @Column(name = "faculty", nullable = false)
    String faculty;
    @Column(name = "national_id", nullable = false)
    String nationalId;
    @Column(name = "university", nullable = false)
    String university;
    @Column(name = "region", nullable = false)
    String region;
    @Column(name = "first_name", nullable = false)
    String firstName;
    @Column(name = "last_name", nullable = false)
    String lastName;
    @Column(name = "mid_name", nullable = false)
    String midName;
    @Column(name = "nick_name", nullable = false)
    String nickName;
    @Column(name = "phone_number", nullable = false)
    String phoneNumber;
    @Column(name = "join_date", nullable = false)
    String joinDate;
    @Column(name = "birth_date", nullable = false)
    String birthDate;
    @Column(name = "tShirt", nullable = false)
    boolean tShirt;
    @Column(name = "mini_camp", nullable = false)
    boolean miniCamp;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "volunteer")
    VolunteerKPI volunteerKPI;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonBackReference
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public Branch branch;
    @OneToOne(mappedBy = "volunteer", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "volunteer_privileges",
            joinColumns = {@JoinColumn(name = "volunteer_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "privilege_id", nullable = false)}
    )
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Privilege> privileges;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "volunteer")
    List<AttendanceStatus> attendanceStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "volunteer_status_id")
    VolunteerStatus volunteerStatus;

    

    public Volunteer() {
    }


    public boolean equals(Volunteer v){
        return (this.id==v.getId());
    }
}
