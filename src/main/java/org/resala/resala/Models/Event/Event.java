package org.resala.resala.Models.Event;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Models.Branch;
import org.resala.Models.Event.EventResult;
import org.resala.Models.Event.EventStatus.AttendanceStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
/*@NamedQueries({
        @NamedQuery(name = "Event.findByBranch", query = "SELECT e FROM Event e  where e.id = :branch_id")
})*/
public class Event {
    @Column(name = "event_id" , insertable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "event_name" , updatable = true)
    String name;
    @Column(name = "event_fromDate")
    String fromDate;
    @Column(name = "event_toDate")
    String toDate;
    @Column(name = "event_callsStartTime")
    String callsStartTime;
    @Column(name = "event_script")
    String script;
    @Column(name = "event_description")
    String description;
    @Column(name = "event_hasCalls")
    boolean hasCalls;
    @Column(name = "event_shareable")
    boolean shareable;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "event")
    List<AttendanceStatus>attendanceStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eventResult_Id")
    EventResult eventResult;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "event_branches",
            joinColumns = {@JoinColumn(name = "event_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "branch_id", nullable = false)}
    )
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "branch_id")
    @JsonIdentityReference(alwaysAsId = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Branch> branches;

}
