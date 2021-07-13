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
    <T> List<T> findAllBy(Class<T> projection);

    <T> List<T> findByMyVolunteerInfo_BranchAndMyVolunteerInfo_VolunteerStatus(Branch branch, VolunteerStatus volunteerStatus, Class<T> projection);

    default  <T> List<T>findAllByBranchAndState(Branch branch, VolunteerStatus volunteerStatus,Class<T> projection){
        return findByMyVolunteerInfo_BranchAndMyVolunteerInfo_VolunteerStatus(branch,volunteerStatus,projection);
    }
    <T> List<T> findByMyVolunteerInfo_Branch_Id(int branchId, Class<T> projection);
    <T> List<T> findByMyVolunteerInfo_VolunteerStatus(VolunteerStatus volunteerStatus, Class<T> projection);
    Optional<LeadVolunteer>findAllByMyVolunteerInfo(Volunteer volunteer);
    <T> List<T> findAllByMyVolunteerInfo_Branch_IdAndCommittee_Id(int branchId,int committeeId,Class<T> projection);

}
