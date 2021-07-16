package org.resala.Repository.Volunteer;

import org.resala.Models.Branch;
import org.resala.Models.Committee.Committee;
import org.resala.Models.Volunteer.LeadVolunteer;
import org.resala.Models.Volunteer.Role;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Models.Volunteer.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeadVolunteerRepo extends JpaRepository<LeadVolunteer, Integer> {
    <T> List<T> findAllByAndMyVolunteerInfo_Organization_Id(Class<T> projection, int orgId);

    List<LeadVolunteer> findAllBy();

    <T> List<T> findByMyVolunteerInfo_BranchAndMyVolunteerInfo_VolunteerStatusAndMyVolunteerInfo_Organization_Id(Branch branch, UserStatus volunteerStatus, Class<T> projection, int orgId);

    default <T> List<T> findAllByBranchAndState(Branch branch, UserStatus volunteerStatus, Class<T> projection, int orgId) {
        return findByMyVolunteerInfo_BranchAndMyVolunteerInfo_VolunteerStatusAndMyVolunteerInfo_Organization_Id(branch, volunteerStatus, projection, orgId);
    }

    <T> List<T> findByMyVolunteerInfo_Branch_IdAndMyVolunteerInfo_Organization_Id(int branchId, Class<T> projection, int orgId);

    <T> List<T> findByMyVolunteerInfo_VolunteerStatusAndMyVolunteerInfo_Organization_Id(UserStatus volunteerStatus, Class<T> projection, int orgId);

    Optional<LeadVolunteer> findAllByMyVolunteerInfo(Volunteer volunteer);

    <T> List<T> findAllByMyVolunteerInfo_BranchAndCommitteeAndMyVolunteerInfo_Organization_IdAndMyVolunteerInfo_RoleOrMyVolunteerInfo_Role(Class<T> projection, Branch branch, Committee committee, int orgId, Role role1, Role role2);

    default <T> List<T> findMyCommitteeTeam(Class<T> projection, Branch branch, Committee committee, int orgId, Role role1, Role role2) {
        return findAllByMyVolunteerInfo_BranchAndCommitteeAndMyVolunteerInfo_Organization_IdAndMyVolunteerInfo_RoleOrMyVolunteerInfo_Role(projection, branch, committee, orgId, role1, role2);
    }
}
