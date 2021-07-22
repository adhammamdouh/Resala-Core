package org.resala.Repository.Event.Attendance;

import org.resala.Models.Branch;
import org.resala.Models.Call.CallResult;
import org.resala.Models.Event.Attendance.AttendanceStatus;
import org.resala.Models.Event.Attendance.EventAttendance;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventAttendanceRepo extends JpaRepository<EventAttendance, Integer> {
    Optional<EventAttendance> findAllByEvent_IdAndAndVolunteer_Id(int eventId, int volunteerId);
    EventAttendance findByEvent_IdAndVolunteer_IdAndBranch_Id(int eventId,int volunteerId,int branchId);
    int countAllByVolunteerAndAttendanceStatus(Volunteer volunteer, AttendanceStatus attendanceStatus);
    int countAllByEventAndEvent_BranchesAndAttendanceStatus(Event event, Branch branch,AttendanceStatus attendanceStatus);
//    int countAllByAttendanceStatusAndVolunteer_CallerCalls_CallerA(AttendanceStatus attendanceStatus,Volunteer caller);
    @Query("SELECT count(ea) FROM event_attendance As ea Join fetch Calls As c on " +
            "ea.event.id = c.event.id and ea.volunteer.id = c.receiver.id  where " +
            "c.caller= :caller and ea.attendanceStatus= :attendanceStatus "+
            "and c.invitationCallResult <> :callResult1 and c.invitationCallResult <> :callResult2")
    int countPresentForLead(@Param("caller")Volunteer caller, @Param("attendanceStatus")AttendanceStatus attendanceStatus,
                            @Param("callResult1")CallResult callResult1,@Param("callResult2")CallResult callResult2);
}
