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

    <T>List<T> findAllByBranch_IdAndEvent_id(int branchId, int eventId,Class<T> projection);

    @Query("SELECT c FROM Calls as c JOIN FETCH event_attendance as e " +
            "WHERE e.volunteer.id=c.receiver.id AND c.branch.id = e.branch.id " +
            " and e.attendanceStatus = :attendanceStatus and c.event.id= :eventId")
    <T> List<T> findAllByAttendanceStatusAndEvent_Id(@Param("attendanceStatus")
                                                             AttendanceStatus attendanceStatus, @Param("eventId") int eventId);

    @Query("SELECT c FROM Calls as c JOIN FETCH event_attendance as e " +
            "WHERE e.volunteer.id=c.receiver.id AND c.branch.id = e.branch.id " +
            " and e.attendanceStatus <> :attendanceStatus and c.event.id= :eventId")
    <T> List<T> findAllByNotAttendanceStatusAndEvent_Id(@Param("attendanceStatus")
                                                             AttendanceStatus attendanceStatus, @Param("eventId") int eventId);

    <T> List<T> findAllByCaller_IdAndEvent_Id
            (int volunteerId, int eventIdn, Class<T> projection);

    Calls findById(int id);

    int countAllByReceiver(Volunteer volunteer);

    default int getVolResponseCount(Volunteer volunteer, CallResult callResult, CallResult callResult2,CallResult callResult3,CallResult callResult4){
        return countAllByReceiverAndInvitationCallResultOrInvitationCallResultOrInvitationCallResultOrInvitationCallResult(volunteer,callResult,callResult2,callResult3,callResult4);
    }
    int countAllByReceiverAndInvitationCallResultOrInvitationCallResultOrInvitationCallResultOrInvitationCallResult(Volunteer volunteer, CallResult callResult, CallResult callResult2, CallResult callResult3, CallResult callResult4);

    int countAllByReceiverAndInvitationCallResult(Volunteer receiver, CallResult callResult);

    int countAllByCaller(Volunteer volunteer);

    int countAllByCallerAndInvitationCallResult(Volunteer caller, CallResult callResult);

    int countAllByEventAndBranch(Event event, Branch branch);

    int countAllByEventAndBranchAndInvitationCallResult(Event event, Branch branch, CallResult callResult);
    default int getAllCalled(Event event, Branch branch, CallResult callResult1, CallResult callResult2,CallResult callResult3,CallResult callResult4){
        return countAllByEventAndBranchAndInvitationCallResultNotAndInvitationCallResultNotAndInvitationCallResultNotAndInvitationCallResultNot(event,branch,callResult1,callResult2,callResult3,callResult4);
    }
    int countAllByEventAndBranchAndInvitationCallResultNotAndInvitationCallResultNotAndInvitationCallResultNotAndInvitationCallResultNot(Event event, Branch branch, CallResult callResult1, CallResult callResult2,CallResult callResult3,CallResult callResult4);
}
