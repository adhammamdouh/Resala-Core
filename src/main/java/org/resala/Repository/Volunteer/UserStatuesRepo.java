package org.resala.Repository.Volunteer;

import org.resala.Models.Volunteer.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStatuesRepo extends JpaRepository<UserStatus, Integer> {
    Optional<UserStatus> findByName(String name);
}
