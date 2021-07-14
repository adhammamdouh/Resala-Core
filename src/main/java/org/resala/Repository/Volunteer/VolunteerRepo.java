package org.resala.Repository.Volunteer;

import org.resala.Models.Branch;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Volunteer.LeadVolunteer;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Models.Volunteer.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface VolunteerRepo extends JpaRepository<Volunteer, Integer> {
    Optional<Volunteer>findByIdAndOrganization_Id(int id,int orgId);
    <T> List<T> findByBranch_idAndOrganization_Id(int branchId, Class<T> projection,int orgId);
    public List<Volunteer> findByBranchAndNetworkTypeAndVolunteerStatus_NameAndOrganization_Id(
            Branch branches,NetworkType networkTypes,String volunteerStatus,int orgId);

    <T> List<T> findAllByOrganization_Id(Class<T> projection, int orgId);

    Optional<Volunteer> findByUser_UserName(String userName);

    <T> List<T> findAllByVolunteerStatus_nameAndOrganization_Id(String name, Class<T> projection,int orgId);

    <T> List<T> findAllByVolunteerStatusAndBranchAndOrganization_Id(UserStatus volunteerStatus, Branch branch, Class<T> projection, int orgId);
    //-------------------------------------------------------
    /*
    @Query("SELECT v FROM Volunteer AS v JOIN FETCH v.branch JOIN FETCH v.user as u WHERE u.userName=:userName")
    Volunteer test(@Param("userName")String userName);//need to be change
    */
    <T>List<T> findAllByLeadVolunteerAndOrganization_Id(LeadVolunteer leadVolunteer,Class<T> projection,int orgId);
    default <T>List<T> getAllNormal(Class<T> projection,int orgId){
        return findAllByLeadVolunteerAndOrganization_Id(null,projection,orgId);
    }

    <T>List<T> findAllByVolunteerStatusAndOrganization_Id(UserStatus volunteerStatus, Class<T> projection, int orgId);

}
