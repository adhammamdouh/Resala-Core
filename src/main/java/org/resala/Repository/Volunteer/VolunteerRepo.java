package org.resala.Repository.Volunteer;

import org.resala.Models.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerRepo extends JpaRepository <Volunteer,Integer>{
    List<Volunteer> findByBranch_id(int branchId);
}
