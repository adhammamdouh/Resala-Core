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
public class NetworkTypeAssignedToVolunteersToEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToMany()
    @JoinTable(name = "network_type_assigned_to_volunteers",
            joinColumns = {@JoinColumn(name = "NetworkTypeAssignedToVolunteersToEvent_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "volunteer_id", nullable = false)})
    List<Volunteer> volunteers;

    @ManyToOne()
    @JoinColumn(name = "network_type_id")
    NetworkType networkType;

    @ManyToOne()
    @JoinColumn(name = "event_id")
    Event event;
}
