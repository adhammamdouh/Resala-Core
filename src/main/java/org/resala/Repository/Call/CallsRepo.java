package org.resala.Repository.Call;

import org.resala.Models.Call.CallType;
import org.resala.Models.Call.Calls;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallsRepo extends JpaRepository<Calls, Integer> {
    List<Calls> findAllByBranch_Id(int id);
    List<Calls> findAllByBranch_IdAndEvent_id(int branchId,int eventId);
    <T> List<T> findAllByCaller_IdAndCallType_IdAndEvent_Id
            (int volunteerId,int CallTypeId,int eventIdn,Class<T> prijection);

    //public List<Calls> findByIds(List<Integer> callsId);
    Calls findById(int id);
}
