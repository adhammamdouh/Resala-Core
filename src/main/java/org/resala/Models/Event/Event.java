package org.resala.Models.Event;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Models.Branch;
import org.resala.Models.Volunteer.VolunteerStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Event implements Serializable {
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    @NotEmpty(message = "Please enter Name")
    String name;

    @Column(name = "from_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Please Enter From Date")
    Date fromDate;

    @Column(name = "to_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Please Enter To Date")
    Date toDate;

    @Column(name = "calls_start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    //----------------
    Date callsStartTime;

    @Column(name = "script")
    @NotEmpty(message = "Please enter Script")
    String script;

    @Column(name = "description")
    @NotEmpty(message = "Please enter Description")
    String description;

    @Column(name = "has_calls")
    @NotNull(message = "Please enter if it has calls or not")
    boolean hasCalls;


    @Column(name = "shareable")
    @NotNull(message = "Please enter if it is shareable or not")
    boolean shareable;

//    @Column
//    boolean ended=false;

    /*@ManyToMany(cascade = CascadeType.ALL, mappedBy = "event")
    List<AttendanceStatus> attendanceStatus;*/

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "event")
//    @JoinColumn(name = "eventResult_Id")

    List<EventResult> eventResult;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "event_branches",
            joinColumns = {@JoinColumn(name = "event_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "branch_id", nullable = false)}
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "Branches can't be null")
    @NotEmpty(message = "Branches can't be empty")
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "branch_id")
    //@JsonIdentityReference(alwaysAsId = true)

    private List<Branch> branches;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_status_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    EventStatus eventStatus;

}
