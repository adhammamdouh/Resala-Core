package org.resala.Repository.Committee;

import org.resala.Models.Committe.Committee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommitteeRepo extends JpaRepository<Committee,Integer> {
    Optional<Committee> findById(int committeeId);
}
