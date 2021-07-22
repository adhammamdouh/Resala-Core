package org.resala.dto.Event.EventStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.resala.Exceptions.NullException;
import org.resala.dto.BranchDTO;
import org.resala.dto.Event.EventDTO;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventAttendanceDTO {
    int id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime dateTime;

    VolunteerDTO volunteer;

    AttendanceStatusDTO attendanceStatus;

    EventDTO event;

    BranchDTO branch;

    public void checkNullForAttendance(){
        if(volunteer==null)
            throw new NullException("Volunteer");
        if(event==null)
            throw new NullException("Event");
        if(branch==null)
            throw new NullException("AttendanceStatus");
    }
}
