package org.resala.Models.Event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Models.Branch;
import org.resala.Models.Organization;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Event implements Serializable {
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Organization organization;

    @Column(name = "name")
    @NotEmpty(message = "Please enter Name")
    String name;

    @Column(name = "from_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Please Enter From Date")
    Date fromDate;

    @Column(name = "to_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Please Enter To Date")
    Date toDate;

    @Column(name = "invitation_calls_start_time", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Please Enter invitation start Date")
    //----------------
    Date invitationStartTime;

    @Column(name = "feed_back_calls_start_time", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Please Enter feed back start Date")
    //----------------
    Date feedBackStartTime;

    @Column(name = "not_attend_calls_start_time", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Please Enter not attend Date")
    //----------------
    Date notAttendStartTime;

    @Column(name = "invitation_calls_end_time", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Please Enter invitation end Date")
    //----------------
    Date invitationEndTime;

    @Column(name = "feed_back_calls_end_time", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Please Enter feed back end Date")
    //----------------
    Date feedBackEndTime;

    @Column(name = "not_attend_calls_end_time", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Please Enter not attend end Date")
    //----------------
    Date notAttendEndTime;

    @Column(name = "script", columnDefinition = "text")
    @NotEmpty(message = "Please enter Script")
    @Lob
    String script;

    @Column(name = "description", columnDefinition = "text")
    @NotEmpty(message = "Please enter Description")
    @Lob
    String description;

    @Column(name = "has_calls")
    @NotNull(message = "Please enter if it has calls or not")
    boolean hasCalls;


    @Column(name = "shareable")
    @NotNull(message = "Please enter if it is shareable or not")
    boolean shareable;



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "event")
    List<EventResult> eventResult;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "event_branches",
            joinColumns = {@JoinColumn(name = "event_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "branch_id", nullable = false)}
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "Branches can't be null")
    @NotEmpty(message = "Branches can't be empty")

    private List<Branch> branches;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_status_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    EventStatus eventStatus;

}
