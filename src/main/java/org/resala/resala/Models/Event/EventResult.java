package org.resala.resala.Models.Event;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class EventResult {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "branch_id")
    int branchId;
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
}
