package org.resala.Repository.Call;

import org.resala.Models.Branch;
import org.resala.Models.Call.CallResult;
import org.resala.Models.Call.Calls;
import org.resala.Models.Event.Attendance.AttendanceStatus;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallsRepo extends JpaRepository<Calls, Integer> {
    List<Calls> findAllByBranch_Id(int id);

    List<Calls> findAllByBranch_IdAndEvent_id(int branchId, int eventId);
    @Query("SELECT c FROM Calls as c INNER JOIN event_attendance as e " +
            "WHERE e.volunteer.id=c.receiver.id" +
            " and e.attendanceStatus = :attendanceStatus and c.event.id= :eventId")
    <T>List<T> findAllByAttendanceStatusAndEvent_Id(@Param("attendanceStatus")
               AttendanceStatus attendanceStatus,@Param("eventId") int eventId);

    <T> List<T> findAllByCaller_IdAndEvent_Id
            (int volunteerId,int eventIdn, Class<T> projection);

    //public List<Calls> findByIds(List<Integer> callsId);
    Calls findById(int id);

    int countAllByReceiverAndInvitationCallResultNot(Volunteer volunteer, CallResult callResult);

    int countAllByReceiverAndInvitationCallResultNotAndInvitationCallResultNot(Volunteer volunteer, CallResult callResult, CallResult callResult2);

    int countAllByReceiverAndInvitationCallResult(Volunteer receiver, CallResult callResult);

    int countAllByCallerAndInvitationCallResultNot(Volunteer volunteer, CallResult callResult);

    int countAllByCallerAndInvitationCallResult(Volunteer caller, CallResult callResult);

    int countAllByEventAndBranch(Event event, Branch branch);
    int countAllByEventAndBranchAndInvitationCallResult(Event event, Branch branch, CallResult callResult);
    int countAllByEventAndBranchAndInvitationCallResultNotAndInvitationCallResultNot(Event event, Branch branch, CallResult callResult1, CallResult callResult2);
}
