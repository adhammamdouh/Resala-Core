package org.resala.Models.Event.EventAttendStatus;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class AttendStatus {
    @Column(name = "attendStatus_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "attendStatus_name")
    String name;
}
