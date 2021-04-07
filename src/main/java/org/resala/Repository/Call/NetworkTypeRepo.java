package org.resala.Repository.Call;

import org.resala.Models.Call.NetworkType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface NetworkTypeRepo extends JpaRepository<NetworkType,Integer> {
    public Optional<NetworkType> findAllById(int id);
    public Optional<NetworkType> findAllByName(String name);
}