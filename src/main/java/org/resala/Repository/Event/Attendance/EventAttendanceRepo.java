package org.resala.Repository.Event.Attendance;

import org.resala.Models.Event.Attendance.AttendanceStatus;
import org.resala.Models.Event.Attendance.EventAttendance;
import org.resala.Models.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventAttendanceRepo extends JpaRepository<EventAttendance, Integer> {
    Optional<EventAttendance> findAllByEvent_IdAndAndVolunteer_Id(int eventId, int volunteerId);
    int countAllByVolunteerAndAttendanceStatus(Volunteer volunteer, AttendanceStatus attendanceStatus);
}
