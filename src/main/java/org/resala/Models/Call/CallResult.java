package org.resala.Models.Call;

import javax.persistence.*;

@Entity
@Table(name = "Call_result")
public class CallResult {
    @Column(name = "callResult_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "callResult_name")
    String name;
}
