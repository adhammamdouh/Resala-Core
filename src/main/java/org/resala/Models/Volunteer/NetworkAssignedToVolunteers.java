package org.resala.Models.Volunteer;

import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Branch;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Event.Event;

import javax.persistence.*;

@Entity
@Table
@Setter
@Getter
public class NetworkAssignedToVolunteers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne()
    @JoinColumn(name = "volunteer_id")
    Volunteer volunteer;

    @ManyToOne()
    @JoinColumn(name = "network_type_id")
    NetworkType networkType;

    @ManyToOne()
    @JoinColumn(name = "event_id")
    Event event;

    @ManyToOne()
    @JoinColumn(name = "branch_id")
    Branch branch;
}
