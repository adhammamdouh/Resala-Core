package org.resala.Repository.Call;

import org.resala.Models.Call.Calls;
import org.resala.Models.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallsRepo extends JpaRepository<Calls,Integer> {
    public List<Calls> findAllByBranch_Id(int id);
    public List<Calls> findByCaller(Volunteer volunteer);

}
