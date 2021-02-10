package org.resala.Repository.Volunteer;

import org.resala.Models.Volunteer.Volunteer;
import org.resala.Models.Volunteer.VolunteerStatus;
import org.resala.StaticNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VolunteerStatuesRepo extends JpaRepository<VolunteerStatus,Integer> {
    Optional<VolunteerStatus>findByName(String name);
    Optional<VolunteerStatus> findByVolunteers_User_UserName(String userName);
}
