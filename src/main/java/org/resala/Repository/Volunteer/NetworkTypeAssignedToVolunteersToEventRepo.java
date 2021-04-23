package org.resala.Repository.Volunteer;

import org.resala.Models.Volunteer.NetworkTypeAssignedToVolunteersToEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NetworkTypeAssignedToVolunteersToEventRepo extends JpaRepository<NetworkTypeAssignedToVolunteersToEvent,Integer> {

    Optional<NetworkTypeAssignedToVolunteersToEvent> getByNetworkType_IdAndEvent_Id(int networkTypeId, int eventId);
    List<NetworkTypeAssignedToVolunteersToEvent> getByEvent_Id(int eventId);
    Optional<NetworkTypeAssignedToVolunteersToEvent> getById(int id);

}
