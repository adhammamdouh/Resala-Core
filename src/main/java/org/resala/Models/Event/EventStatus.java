package org.resala.Models.Event;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "event_status")
@Getter
@Setter
public class EventStatus {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Please Enter Event Status Name")
    String name;
    @OneToMany(mappedBy = "eventStatus", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference
    List<Event> events;
}
