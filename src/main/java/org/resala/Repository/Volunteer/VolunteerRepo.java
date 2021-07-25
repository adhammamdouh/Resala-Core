package org.resala.Repository.Volunteer;

import org.resala.Models.Branch;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Volunteer.LeadVolunteer;
import org.resala.Models.Volunteer.Role;
import org.resala.Models.Volunteer.UserStatus;
import org.resala.Models.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerRepo extends JpaRepository<Volunteer, Integer> {
    Optional<Volunteer> findByIdAndOrganization_Id(int id, int orgId);

    <T> List<T> findByBranch_idAndOrganization_Id(int branchId, Class<T> projection, int orgId);

    List<Volunteer> findAllByRoleAndBranchAndNetworkTypeAndVolunteerStatusAndOrganization_Id(
            Role role, Branch branches, NetworkType networkTypes, UserStatus volunteerStatus, int orgId);

    Optional<Volunteer> findByPhoneNumberAndOrganization_Id(String phoneNumber, int orgId);

    Boolean existsByPhoneNumberAndOrganization_Id(String phoneNu, int orgId);

    <T> List<T> findAllByOrganization_Id(Class<T> projection, int orgId);

    Optional<Volunteer> findByUser_UserName(String userName);

    <T> List<T> findAllByVolunteerStatus_nameAndOrganization_Id(String name, Class<T> projection, int orgId);

    <T> List<T> findAllByVolunteerStatusAndBranchAndOrganization_Id(UserStatus volunteerStatus, Branch branch, Class<T> projection, int orgId);

    //-------------------------------------------------------

    @Query(value = "SELECT v from Volunteer AS v left join LeadVolunteer l where l.id is null ")
    <T> List<T> getAllNormal(Class<T> projection, int orgId);


    <T> List<T> findAllByVolunteerStatusAndOrganization_Id(UserStatus volunteerStatus, Class<T> projection, int orgId);

    Optional<Volunteer> findByUser_id(int id);
}
