package org.resala.Service.Commiittee;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Branch;
import org.resala.Models.Committe.Committee;
import org.resala.Repository.Committee.CommitteeRepo;
import org.resala.Service.CommonCRUDService;
import org.resala.Service.CommonService;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommitteeService  {
    @Autowired
    CommitteeRepo committeeRepo;


    public Committee getById(int id) {
        Optional<Committee> optionalCommittee = committeeRepo.findById(id);
        if (!optionalCommittee.isPresent())
            throw new MyEntityNotFoundException("Committee "+ StaticNames.notFound);
        return optionalCommittee.get();
    }

}
