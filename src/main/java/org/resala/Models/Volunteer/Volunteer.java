package org.resala.Models.Volunteer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Annotation.PhoneNumber.Phone;
import org.resala.Models.Address.Address;
import org.resala.Models.Branch;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Event.Attendance.EventAttendance;
import org.resala.Models.KPI.VolunteerKPI;
import org.resala.Models.Organization;
import org.resala.Models.Privilege.Privilege;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class Volunteer implements Serializable {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Organization organization;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    Address address;
    @Column(name = "gender")
    int gender;
    @Column(name = "faculty")
    @NotEmpty(message = "Please enter Faculty")
    String faculty;
    @Column(name = "university")
    @NotEmpty(message = "Please enter University")
    String university;

    @Column(name = "comments")
    String comments;

    @Column(name = "national_id")
    @NotEmpty(message = "Please enter National Id")
    String nationalId;

    @Column(name = "first_name")
    @NotEmpty(message = "Please enter First Name")
    String firstName;
    @Column(name = "mid_name")
    @NotEmpty(message = "Please enter Mid Name")
    String midName;
    @Column(name = "last_name")
    @NotEmpty(message = "Please enter Last Name")
    String lastName;
    @Column(name = "nick_name")
    String nickName;

    @Column(name = "phone_number", unique = true)
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
    @Formula("YEAR(CURDATE()) - YEAR(birth_date)")
    int age;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "shirt_id", nullable = false)
    @NotNull(message = "T-Shirt Can't be null")
    Shirt shirt;
    @Column(name = "mini_camp")
    boolean miniCamp;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "kpi_id")
    VolunteerKPI volunteerKPI;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "education_level_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull(message = "Please Enter education level")
    EducationLevel educationLevel;

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


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "volunteer", fetch = FetchType.LAZY)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<EventAttendance> eventAttendances;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "volunteer_status_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    UserStatus volunteerStatus;

    private void mapVolData(Volunteer volunteer) {
        for (Method getMethod : volunteer.getClass().getMethods()) {
            if (getMethod.getName().startsWith("get")) {
                try {
                    Method setMethod = this.getClass().getMethod(getMethod.getName().replace("get", "set"), getMethod.getReturnType());
                    setMethod.invoke(this, getMethod.invoke(volunteer, (Object[]) null));

                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    //not found set
                }
            }
        }
    }

    public Volunteer(Volunteer volunteer) {
        mapVolData(volunteer);
    }

    public Volunteer() {

    }

    public void setVolunteer(Volunteer volunteer) {
        mapVolData(volunteer);
    }
}
