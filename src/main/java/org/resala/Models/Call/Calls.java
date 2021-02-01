package org.resala.Models.Call;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.*;

@Entity
@Table(name = "calls")
public class Calls {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "event_id", nullable = false)
    Event event;


    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Volunteer.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "caller_id", nullable = false)
    Volunteer caller;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Volunteer.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "receiver_id", nullable = false)

    Volunteer receiver;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "call_type_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    CallType callType;
    @Column(name = "call_time")
    String time;
    @Column(name = "call_time_un_editable_before")
    boolean timeUnEditableBefore;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "call_result_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    CallResult callResult;

}
