package org.resala.Repository.Call;

import org.resala.Models.Call.CallResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CallResultRepo extends JpaRepository<CallResult, Integer> {
    Optional<CallResult> getById(int id);
    Optional<CallResult>getByName(String name);
}
