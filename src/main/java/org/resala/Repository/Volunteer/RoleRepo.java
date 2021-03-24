package org.resala.Repository.Volunteer;

import org.resala.Models.Volunteer.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
    @Transactional
    @Query("SELECT r FROM Role r JOIN FETCH r.volunteers v WHERE v.user.userName = :userName")
    Role  findByVolunteers_User_userName
            (@Param("userName") String userName);

}
