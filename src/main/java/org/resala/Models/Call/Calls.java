package org.resala.Models.Call;


import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Models.Branch;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.Volunteer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "calls")
@Getter
@Setter
public class Calls {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String invitationComment;

    @Column
    String feedBackComment;

    @Column
    String notAttendComment;
    @Column(columnDefinition = "boolean default false")
    boolean respond;
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "event_id", nullable = false)
    Event event;


    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "caller_id")
    Volunteer caller;

    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "receiver_id", nullable = false)

    Volunteer receiver;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "call_type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    CallType callType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "network_type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    NetworkType networkType;

    @Column(name = "invitation_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date invitationTime;

    @Column(name = "feedBack_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date feedBackTime;

    @Column(name = "not_attend_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date notAttendTime;


    @Column(name = "time_un_editable_before")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date timeUnEditableBefore;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "call_result_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    CallResult callResult;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id" )
//    @JsonIdentityReference(alwaysAsId = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Branch branch;

}
