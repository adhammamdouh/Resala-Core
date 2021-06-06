package org.resala.Repository.Volunteer;

import org.resala.Models.Branch;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Volunteer.LeadVolunteer;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Models.Volunteer.VolunteerStatus;
import org.resala.Projections.VolunteerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface VolunteerRepo extends JpaRepository<Volunteer, Integer> {
    <T> List<T> findByBranch_id(int branchId, Class<T> projection);
    public List<Volunteer> findByBranchAndNetworkTypeAndVolunteerStatus_Name(
            Branch branches,NetworkType networkTypes,String volunteerStatus);
    @Query("SELECT v \n" +
            "FROM Volunteer v")
    <T> List<T> findAllBy(Class<T> projection);

    Optional<Volunteer> findByUser_UserName(String userName);

    <T> List<T> findAllByVolunteerStatus_name(String name, Class<T> projection);

    <T> List<T> findAllByVolunteerStatusAndBranch(VolunteerStatus volunteerStatus, Branch branch, Class<T> projection);
    //-------------------------------------------------------
    /*
    @Query("SELECT v FROM Volunteer AS v JOIN FETCH v.branch JOIN FETCH v.user as u WHERE u.userName=:userName")
    Volunteer test(@Param("userName")String userName);//need to be change
    */
    <T>List<T> findAllByLeadVolunteer(LeadVolunteer leadVolunteer,Class<T> projection);
    default <T>List<T> getAllNormal(Class<T> projection){
        return findAllByLeadVolunteer(null,projection);
    }

    <T>List<T> findAllByVolunteerStatus(VolunteerStatus volunteerStatus, Class<T> projection);
}
