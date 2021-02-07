package org.resala.resala.Repository;

import org.resala.Models.Volunteer.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String userName) ;
    @Modifying
    @Query("update User u set u.userName = ?2, u.password = ?3 where u.userName = ?1")
    void updateUserByUserName(String userName1,String userName2, String password);


}


