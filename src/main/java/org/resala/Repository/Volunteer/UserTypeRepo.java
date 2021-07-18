package org.resala.Repository.Volunteer;

import org.resala.Models.Volunteer.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTypeRepo extends JpaRepository<UserType,Integer> {
    Optional<UserType> findByName(String name);
}
