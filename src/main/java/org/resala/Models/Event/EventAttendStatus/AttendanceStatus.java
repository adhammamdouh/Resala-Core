package org.resala.Models.Event.EventAttendStatus;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.*;

@Table
@Entity(name = "attendance_status")
@Getter
@Setter
public class AttendanceStatus {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "volunteer_id")
    Volunteer volunteer;
    @Column(name = "comment")
    String comment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attend_status_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    AttendStatus attendStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Event event;

}
