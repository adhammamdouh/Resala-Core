package org.resala.Repository.Volunteer;

import org.resala.Models.Volunteer.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepo extends JpaRepository<UserType,Integer> {
}
