package org.resala.Controllers.Event.Attendance;

import org.resala.Service.Event.Attendance.EventAttendanceService;
import org.resala.StaticNames;
import org.resala.dto.Event.EventStatus.EventAttendanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventAttendance")
public class EventAttendanceController {
    @Autowired
    EventAttendanceService eventAttendanceService;
    @RequestMapping(value = "/attended",method = RequestMethod.PUT)
    @PreAuthorize("hasRole('" + StaticNames.makeEventAttendance + "')")
    public ResponseEntity<Object> makeAttendToVolunteer(EventAttendanceDTO eventAttendanceDTO) {
        return eventAttendanceService.makeAttendToVolunteer(eventAttendanceDTO,StaticNames.attendedTheEvent);
    }
    @RequestMapping(value = "/notAttended",method = RequestMethod.PUT)
    @PreAuthorize("hasRole('" + StaticNames.makeEventAttendance + "')")
    public ResponseEntity<Object> makeNotAttendToVolunteer(EventAttendanceDTO eventAttendanceDTO) {
        return eventAttendanceService.makeAttendToVolunteer(eventAttendanceDTO,StaticNames.notAttendedTheEvent);
    }
}
