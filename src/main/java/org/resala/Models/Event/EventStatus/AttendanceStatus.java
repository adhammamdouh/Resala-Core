package org.resala.Models.Event.EventStatus;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.*;
import java.util.List;

@Table
@Entity(name = "attendance_status")
@Getter
@Setter
public class AttendanceStatus {
    @Column(name = "attendanceStatus_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "volunteer_d")
    Volunteer volunteer;
    @Column(name = "attendanceStatus_comment")
    String comment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attend_status_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    AttendStatus attendStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "event_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Event event;

}
