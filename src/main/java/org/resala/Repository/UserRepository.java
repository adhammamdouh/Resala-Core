package org.resala.Repository;

import org.resala.Models.Branch;
import org.resala.Models.MyUserDetails;
import org.resala.Models.Volunteer.User;
import org.resala.Models.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
//    User findByUserNameAndOrganization_Id(String userName, int orgId);

    User findByUserName(String username);
    /*@Modifying
    @Query("update User u set u.userName = ?2, u.password = ?3 where u.userName = ?1")
    void updateUserByUserName(String userName1,String userName2, String password);*/


}


