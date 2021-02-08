package org.resala.Models.Event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Models.Branch;
import org.resala.Models.Event.EventStatus.AttendanceStatus;
import org.resala.Models.Volunteer.Role;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Event {
    @Column(name = "event_id", insertable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name", updatable = true)
    String name;
    @Column(name = "from_date")
    //@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date fromDate;
    @Column(name = "to_date")
    //@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date toDate;
    @Column(name = "calls_start_time")
    //@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date callsStartTime;
    @Column(name = "script")
    String script;
    @Column(name = "description")
    String description;
    @Column(name = "has_calls")
    boolean hasCalls;
    @Column(name = "shareable")
    boolean shareable;

    /*@ManyToMany(cascade = CascadeType.ALL, mappedBy = "event")
    List<AttendanceStatus> attendanceStatus;*/

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eventResult_Id")
    EventResult eventResult;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "event_branches",
            joinColumns = {@JoinColumn(name = "event_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "branch_id", nullable = false)}
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "branch_id")
    //@JsonIdentityReference(alwaysAsId = true)
    private List<Branch> branches;

}
