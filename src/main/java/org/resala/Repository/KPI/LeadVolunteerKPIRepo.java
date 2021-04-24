package org.resala.Repository.KPI;

import org.resala.Models.KPI.LeadVolunteerKPI;
import org.resala.Models.KPI.VolunteerKPI;
import org.resala.Models.Volunteer.LeadVolunteer;
import org.resala.Models.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeadVolunteerKPIRepo extends JpaRepository<LeadVolunteerKPI,Integer> {
    Optional<LeadVolunteerKPI> findByLeadVolunteer(LeadVolunteer leadVolunteer);
}
