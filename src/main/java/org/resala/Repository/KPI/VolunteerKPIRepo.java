package org.resala.Repository.KPI;

import org.resala.Models.KPI.VolunteerKPI;
import org.resala.Models.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VolunteerKPIRepo extends JpaRepository<VolunteerKPI,Integer> {
    Optional<VolunteerKPI> findByVolunteer(Volunteer volunteer);
}
