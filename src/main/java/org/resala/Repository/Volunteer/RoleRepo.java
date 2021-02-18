package org.resala.Repository.Volunteer;

import org.resala.Models.Volunteer.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
    /*@Query("SELECT r FROM Role AS r inner join volunteer on volunteer.role_id = r.id inner join user " +
            "inner join role on volunteer.id = user.volunteer_id")
    Optional<Role> findByVolunteers_User_userName(String userName);*/
}
