package org.resala.Models.Event;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Branch;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class EventResult {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "response_percentage")
    double responsePercentage;
    @Column(name = "attendance_percentage")
    double attendancePercentage;
    @Column(name = "attracting_percentage")
    double attractingPercentage;
    @Column(name = "ensure_percentage")
    double ensurePercentage;
    @Column(name = "present_count")
    int presentCount;
    @Column(name = "calls_count")
    int callsCount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    @JsonBackReference
    Event event;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
//    @JsonBackReference
    Branch branch;
}
