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
import java.util.Optional;

@Repository
public interface EventRepo extends JpaRepository<Event, Integer> {
    List<Event>findAllByOrganization_Id(int orgId);
    Optional<Event>findByIdAndOrganization_id(int id,int orgId);
    List<Event> findAllByBranches_idAndOrganization_Id(@Param("branchId") int branchId,int orgId);

    List<Event> findAllByEventStatus_nameAndOrganization_Id(String name,int orgId);

    List<Event> findAllByEventStatusAndOrganization_Id(EventStatus eventStatus,int orgId);


    List<Event> findAllByBranches_idAndEventStatusAndOrganization_Id(@Param("branch_id") int branchId, EventStatus eventStatus,int orgId);


    List<Event> findAllByShareableAndEventStatusAndAndBranches_idAndOrganization_Id(boolean isShareable,EventStatus eventStatus,@Param("branch_id") int branchId,int orgId);
    List<Event> findAllByShareableAndEventStatusAndOrganization_Id(boolean isShareable,EventStatus eventStatus,int orgId);

    List<Event>findAllByEventStatusAndEventResultAndOrganization_Id(EventStatus eventStatus, EventResult eventResult,int orgId);

}
