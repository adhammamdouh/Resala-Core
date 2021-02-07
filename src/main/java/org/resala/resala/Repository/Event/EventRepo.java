package org.resala.resala.Repository.Event;

import org.resala.Models.Event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event,Integer> {
    List<Event> findByBranches_id(@Param("branch_id") int branchId);
}
