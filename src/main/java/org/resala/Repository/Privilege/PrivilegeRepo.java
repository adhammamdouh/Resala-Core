package org.resala.Repository.Privilege;

import org.resala.Models.Privilege.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrivilegeRepo extends JpaRepository<Privilege,Integer> {
    Optional<Privilege>findByNameAndOrganization_Id(String name,int orgId);
    Optional<Privilege>findByIdAndOrganization_Id(int id,int orgId);

    List<Privilege> findAllByIdInAndOrganization_Id(List<Integer> ids, int organizationId);
}
