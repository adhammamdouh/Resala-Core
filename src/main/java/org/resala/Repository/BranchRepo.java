package org.resala.Repository;

import org.resala.Models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchRepo extends JpaRepository<Branch,Integer> {
    Optional<Branch> findByVolunteers_User_UserName(String userName);
}
