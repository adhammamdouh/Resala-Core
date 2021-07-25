package org.resala.Repository.Call;

import org.resala.Models.Call.CallType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CallTypeRepo extends JpaRepository<CallType,Integer> {
    public Optional<CallType> findByName(String name);
}
