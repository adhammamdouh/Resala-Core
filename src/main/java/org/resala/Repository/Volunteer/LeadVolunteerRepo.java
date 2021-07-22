package org.resala.Repository.Volunteer;

import org.resala.Models.Branch;
import org.resala.Models.Committee.Committee;
import org.resala.Models.Volunteer.LeadVolunteer;
import org.resala.Models.Volunteer.Role;
import org.resala.Models.Volunteer.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeadVolunteerRepo extends JpaRepository<LeadVolunteer, Integer> {
    <T> List<T> findAllByOrganization_Id(Class<T> projection, int orgId);

    List<LeadVolunteer> findAllBy();

    <T> List<T> findAllByBranchAndVolunteerStatusAndOrganization_Id(Branch branch, UserStatus volunteerStatus, Class<T> projection, int orgId);

    default <T> List<T> findAllByBranchAndState(Branch branch, UserStatus volunteerStatus, Class<T> projection, int orgId) {
        return findAllByBranchAndVolunteerStatusAndOrganization_Id(branch, volunteerStatus, projection, orgId);
    }

    <T> List<T> findAllByBranch_IdAndOrganization_Id(int branchId, Class<T> projection, int orgId);

    <T> List<T> findByVolunteerStatusAndOrganization_Id(UserStatus volunteerStatus, Class<T> projection, int orgId);

    Optional<LeadVolunteer> findById(int volunteerId);
    List<LeadVolunteer>findAllByRole(Role role1);
    <T> List<T> findAllByBranchAndCommitteeAndOrganization_IdAndRoleOrRole(Class<T> projection, Branch branch, Committee committee, int orgId, Role role1, Role role2);

    default <T> List<T> findMyCommitteeTeam(Class<T> projection, Branch branch, Committee committee, int orgId, Role role1, Role role2) {
        return findAllByBranchAndCommitteeAndOrganization_IdAndRoleOrRole(projection, branch, committee, orgId, role1, role2);
    }
}
