package org.resala.Models.Call;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity(name = "call_type")
@Getter
@Setter
public class CallType implements Serializable {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name",unique = true)
    String name;

    public boolean equals(CallType callType){
        return this.name.equals(callType.name);
    }


}
