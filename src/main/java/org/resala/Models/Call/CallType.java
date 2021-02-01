package org.resala.Models.Call;

import javax.persistence.*;

@Table
@Entity(name = "Call_type")
public class CallType {
    @Column(name = "callType_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "callType_name",unique = true)
    String name;


}
