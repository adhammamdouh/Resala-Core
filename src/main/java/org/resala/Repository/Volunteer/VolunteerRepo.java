package org.resala.Repository.Volunteer;

import org.resala.Models.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepo extends JpaRepository <Volunteer,Integer>{
    List<Volunteer> findByBranch_id(int branchId);
}
