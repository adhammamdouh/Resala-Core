package org.resala.Repository.Volunteer;

import org.resala.Models.Volunteer.VolunteerAssignedCallsToEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VolunteerAssignedCallsToEventRepo extends JpaRepository<VolunteerAssignedCallsToEvent,Integer> {

    Optional<VolunteerAssignedCallsToEvent> getByVolunteer_IdAndEvent_Id(int volunteerId,int eventId);
    List<VolunteerAssignedCallsToEvent> getByEvent_Id(int eventId);
    Optional<VolunteerAssignedCallsToEvent> getById(int id);

}
