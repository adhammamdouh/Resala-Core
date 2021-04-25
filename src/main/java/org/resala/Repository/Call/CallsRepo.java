package org.resala.Repository.Call;

import org.aspectj.weaver.ast.Call;
import org.resala.Models.Branch;
import org.resala.Models.Call.CallResult;
import org.resala.Models.Call.CallType;
import org.resala.Models.Call.Calls;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Event.Attendance.AttendanceStatus;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallsRepo extends JpaRepository<Calls, Integer> {
    List<Calls> findAllByBranch_Id(int id);

    List<Calls> findAllByBranch_IdAndEvent_id(int branchId, int eventId);

    <T> List<T> findAllByCaller_IdAndCallType_IdAndEvent_Id
            (int volunteerId, int CallTypeId, int eventIdn, Class<T> prijection);

    //public List<Calls> findByIds(List<Integer> callsId);
    Calls findById(int id);

    int countAllByReceiverAndCallResultNot(Volunteer volunteer, CallResult callResult);

    int countAllByReceiverAndCallResultNotAndCallResultNot(Volunteer volunteer, CallResult callResult, CallResult callResult2);

    int countAllByReceiverAndCallResult(Volunteer receiver, CallResult callResult);

    int countAllByCallerAndCallResultNot(Volunteer volunteer, CallResult callResult);

    int countAllByCallerAndCallResult(Volunteer caller, CallResult callResult);

    int countAllByEventAndBranch(Event event, Branch branch);
    int countAllByEventAndBranchAndCallResult(Event event, Branch branch,CallResult callResult);
    int countAllByEventAndBranchAndCallResultNotAndCallResultNot(Event event,Branch branch,CallResult callResult1,CallResult callResult2);
}
