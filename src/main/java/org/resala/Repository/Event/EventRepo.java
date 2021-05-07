package org.resala.Repository.Event;

import org.resala.Models.Branch;
import org.resala.Models.Event.Event;

import org.resala.Models.Event.EventStatus;
import org.resala.Models.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event, Integer> {
    List<Event> findByBranches_id(@Param("branch_id") int branchId);

    List<Event> findAllByEventStatus_name(String name);

    List<Event> findAllByEventStatus(EventStatus eventStatus);

    //@Query("select e from Event e where e.eventStatus.name = :name  and e.branches in :ids")

    List<Event> findAllByBranches_idAndEventStatus(@Param("branch_id") int branchId, EventStatus eventStatus);


    List<Event> findAllByShareableAndEventStatusAndAndBranches_id(boolean isShareable,EventStatus eventStatus,@Param("branch_id") int branchId);
    List<Event> findAllByShareableAndEventStatus(boolean isShareable,EventStatus eventStatus);
    List<Event> findAllByEventStatus_nameAndBranchesIn(String name, List<Branch> branches);

}
