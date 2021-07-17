package org.resala.Models.Call;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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


    @Column(name = "invitation_time_un_editable_before")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date invitationUnEditableBefore;

    @Column(name = "feed_back_time_un_editable_before")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date feedBackUnEditableBefore;
    
    @Column(name = "not_attend_time_un_editable_before")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date notAttendUnEditableBefore;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invitation_call_result_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    CallResult invitationCallResult;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feed_back_call_result_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    CallResult feedBackCallResult;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "not_attend_call_result_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    CallResult notAttendCallResult;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id" )
//    @JsonIdentityReference(alwaysAsId = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Branch branch;

}
