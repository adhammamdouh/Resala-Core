package org.resala.resala.Models.Call;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity(name = "Call_type")
@Getter
@Setter
public class CallType {
    @Column(name = "callType_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "callType_name",unique = true)
    String name;


}
