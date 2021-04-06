package org.resala.Repository.Event;

import org.resala.Models.Event.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventStatusRepo extends JpaRepository<EventStatus, Integer> {
    Optional<EventStatus> findByName(String name);
}
