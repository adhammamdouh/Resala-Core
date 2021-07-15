package org.resala.Repository.Volunteer;

import org.resala.Models.Volunteer.Shirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShirtRepo extends JpaRepository<Shirt, Integer> {
}
