package org.resala.Repository.Volunteer;

import org.resala.Models.Volunteer.LeadVolunteer;
import org.resala.Models.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeadVolunteerRepo extends JpaRepository<LeadVolunteer, Integer> {

    <T> List<T> findAllBy(Class<T> projection);
    <T> List<T> findByMyVolunteerInfo_Branch_IdAndMyVolunteerInfo_VolunteerStatus_Name(int branchId,String name, Class<T> projection);
    default  <T> List<T>findAllByBranchAndState(int branchId,String stateName,Class<T> projection){
        return findByMyVolunteerInfo_Branch_IdAndMyVolunteerInfo_VolunteerStatus_Name(branchId,stateName,projection);
    }
    <T> List<T> findByMyVolunteerInfo_Branch_Id(int branchId, Class<T> projection);
    <T> List<T> findByMyVolunteerInfo_VolunteerStatus_Name(String name, Class<T> projection);
}
