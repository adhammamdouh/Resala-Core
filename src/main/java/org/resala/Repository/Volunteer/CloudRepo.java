package org.resala.Repository.Volunteer;

import org.resala.Models.Volunteer.Cloud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudRepo extends JpaRepository<Cloud,Integer> {
}
