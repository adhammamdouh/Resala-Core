package org.resala.Repository.Volunteer;

import org.resala.Models.Branch;
import org.resala.Models.Volunteer.LeadVolunteer;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Models.Volunteer.VolunteerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LeadVolunteerRepo extends JpaRepository<LeadVolunteer, Integer> {
    <T> List<T> findAllByAndMyVolunteerInfo_Organization_Id(Class<T> projection,int orgId);

    List<LeadVolunteer> findAllBy();

    <T> List<T> findByMyVolunteerInfo_BranchAndMyVolunteerInfo_VolunteerStatusAndMyVolunteerInfo_Organization_Id(Branch branch, VolunteerStatus volunteerStatus, Class<T> projection,int orgId);

    default <T> List<T> findAllByBranchAndState(Branch branch, VolunteerStatus volunteerStatus, Class<T> projection,int orgId) {
        return findByMyVolunteerInfo_BranchAndMyVolunteerInfo_VolunteerStatusAndMyVolunteerInfo_Organization_Id(branch, volunteerStatus, projection,orgId);
    }

    <T> List<T> findByMyVolunteerInfo_Branch_IdAndMyVolunteerInfo_Organization_Id(int branchId, Class<T> projection,int orgId);

    <T> List<T> findByMyVolunteerInfo_VolunteerStatusAndMyVolunteerInfo_Organization_Id(VolunteerStatus volunteerStatus, Class<T> projection,int orgId);

    Optional<LeadVolunteer> findAllByMyVolunteerInfo(Volunteer volunteer);

    <T> List<T> findAllByMyVolunteerInfo_Branch_IdAndCommittee_IdAndMyVolunteerInfo_Organization_Id(int branchId, int committeeId, Class<T> projection,int orgId);
}
