package org.resala.resala.Repository.Privilege;

import org.resala.Models.Privilege.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepo extends JpaRepository<Privilege,Integer> {
    Optional<Privilege>findByName(String name);
}
