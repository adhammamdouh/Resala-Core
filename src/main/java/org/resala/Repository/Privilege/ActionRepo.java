package org.resala.Repository.Privilege;

import org.resala.Models.Privilege.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepo extends JpaRepository<Action, Integer> {
}
