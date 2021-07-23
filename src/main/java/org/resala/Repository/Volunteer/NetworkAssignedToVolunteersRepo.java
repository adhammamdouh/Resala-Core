package org.resala.Repository.Volunteer;

import org.resala.Models.Volunteer.NetworkAssignedToVolunteers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NetworkAssignedToVolunteersRepo extends JpaRepository<NetworkAssignedToVolunteers,Integer> {

    Optional<NetworkAssignedToVolunteers> getByNetworkType_IdAndEvent_Id(int networkTypeId, int eventId);
    List<NetworkAssignedToVolunteers> getAllByEvent_IdAndBranch_id(int eventId, int branchId);
    Optional<NetworkAssignedToVolunteers> getByEvent_IdAndBranch_IdAndNetworkType_Id(int eventId,int branchId,int networkId);
    Optional<NetworkAssignedToVolunteers> getById(int id);

    <T>List<T> getAllByEvent_IdAndBranch_Id(int eventId,int branchId, Class<T> projection);

}
