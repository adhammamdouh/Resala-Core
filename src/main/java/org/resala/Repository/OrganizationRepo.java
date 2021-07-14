package org.resala.Repository;

import org.resala.Models.Branch;
import org.resala.Models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepo extends JpaRepository<Organization,Integer> {
}
