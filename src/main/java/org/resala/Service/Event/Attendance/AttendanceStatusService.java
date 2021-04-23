package org.resala.Service.Event.Attendance;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Event.Attendance.AttendanceStatus;
import org.resala.Models.Event.Attendance.EventAttendance;
import org.resala.Repository.Event.Attendance.AttendanceStatusRepo;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttendanceStatusService {
    @Autowired
    AttendanceStatusRepo attendanceStatusRepo;
    public AttendanceStatus getByName(String name){
        Optional<AttendanceStatus> attendanceStatusOptional = attendanceStatusRepo.findAllByName(name);
        if (!attendanceStatusOptional.isPresent())
            throw new MyEntityNotFoundException("EventAttendance " + StaticNames.notFound);
        return attendanceStatusOptional.get();
    }
}
