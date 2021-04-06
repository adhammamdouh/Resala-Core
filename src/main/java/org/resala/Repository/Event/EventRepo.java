package org.resala.Repository.Event;

import org.resala.Models.Branch;
import org.resala.Models.Event.Event;

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

    //@Query("select e from Event e where e.eventStatus.name = :name  and e.branches in :ids")

    List<Event> findAllByBranches_idAndEventStatus_name(@Param("branch_id") int branchId, String name);


    List<Event> findAllByEventStatus_nameAndBranchesIn(String name, List<Branch> branches);
}
