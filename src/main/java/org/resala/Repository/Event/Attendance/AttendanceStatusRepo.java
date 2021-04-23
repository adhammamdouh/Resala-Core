package org.resala.Repository.Event.Attendance;

import org.resala.Models.Event.Attendance.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceStatusRepo extends JpaRepository<AttendanceStatus,Integer> {
    Optional<AttendanceStatus>findAllByName(String name);
}
