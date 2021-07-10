package org.resala.Repository.Event;

import org.resala.Models.Branch;
import org.resala.Models.Event.Event;

import org.resala.Models.Event.EventResult;
import org.resala.Models.Event.EventStatus;
import org.resala.Models.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event, Integer> {
    /*@Query(value = "select * from event as e inner join event_branches as b on e.id=b.event_id inner join event_result as er on " +
            "er.event_id = e.id where b.branch_id =:branchId and er.branch_id= :branchId",nativeQuery=true)*/

    List<Event> findByBranches_id(@Param("branchId") int branchId);

    List<Event> findAllByEventStatus_name(String name);

    List<Event> findAllByEventStatus(EventStatus eventStatus);

    //@Query("select e from Event e where e.eventStatus.name = :name  and e.branches in :ids")

    List<Event> findAllByBranches_idAndEventStatus(@Param("branch_id") int branchId, EventStatus eventStatus);


    List<Event> findAllByShareableAndEventStatusAndAndBranches_id(boolean isShareable,EventStatus eventStatus,@Param("branch_id") int branchId);
    List<Event> findAllByShareableAndEventStatus(boolean isShareable,EventStatus eventStatus);
    List<Event> findAllByEventStatus_nameAndBranchesIn(String name, List<Branch> branches);

    List<Event>findAllByEventStatusAndEventResult(EventStatus eventStatus, EventResult eventResult);

}
