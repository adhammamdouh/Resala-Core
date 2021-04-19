package org.resala.Models.Volunteer;

import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Event.Event;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Setter
@Getter
public class VolunteerAssignedCallsToEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToMany()
    @JoinTable(name = "volunteerAssignedCalls_networkType",
            joinColumns = {@JoinColumn(name = "VolunteerAssignedCallsToEvent_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "networkType_id", nullable = false)})
    List<NetworkType> networkTypeList;

    @ManyToOne()
    @JoinColumn(name = "volunteer_id")
    Volunteer volunteer;

    @ManyToOne()
    @JoinColumn(name = "event_id")
    Event event;
}
