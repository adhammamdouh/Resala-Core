package org.resala.Repository.Volunteer;

import org.resala.Models.Volunteer.Volunteer;
import org.resala.Projections.outer;
import org.resala.Projections.testName;
import org.resala.Projections.testName2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;

import java.util.Optional;

@Repository
public interface VolunteerRepo extends JpaRepository<Volunteer, Integer> {
    <T> List<T> findByBranch_id(int branchId, Class<T> projection);

    <T> List<T> findAllBy(Class<T> projection);

    Optional<Volunteer> findByUser_UserName(String userName);

    <T> List<T> findAllByVolunteerStatus_name(String name, Class<T> projection);

    <T> List<T> findAllByVolunteerStatus_nameAndBranch_id(String name, int branchId, Class<T> projection);
    //-------------------------------------------------------
    /*
    @Query("SELECT v FROM Volunteer AS v JOIN FETCH v.branch JOIN FETCH v.user as u WHERE u.userName=:userName")
    Volunteer test(@Param("userName")String userName);//need to be change
    */

}
