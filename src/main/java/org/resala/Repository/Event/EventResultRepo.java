package org.resala.Repository.Event;

import org.resala.Models.Branch;
import org.resala.Models.Event.Event;
import org.resala.Models.Event.EventResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventResultRepo extends JpaRepository<EventResult,Integer> {
    Optional<EventResult>getByEventAndBranch(Event event, Branch branch);
}
