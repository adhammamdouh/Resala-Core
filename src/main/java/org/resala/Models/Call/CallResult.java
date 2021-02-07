package org.resala.Models.Call;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "call_result")
@Getter
@Setter
public class CallResult {
    @Column(name = "callResult_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "callResult_name")
    String name;
}
