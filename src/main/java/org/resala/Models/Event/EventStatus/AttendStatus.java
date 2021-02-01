package org.resala.Models.Event.EventStatus;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table
public class AttendStatus {
    @Column(name = "attendStatus_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "attendStatus_name")
    String name;
}
