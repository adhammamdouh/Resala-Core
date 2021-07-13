package org.resala.Repository;

import org.resala.Models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepo extends JpaRepository<Branch,Integer> {
    Optional<Branch> findByIdAndOrganization_Id(int id,int orgId);
    List<Branch> findAllByIdInAndOrganization_Id(List<Integer> ids, int orgId);
}
