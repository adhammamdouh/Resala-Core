package org.resala.Service.Volunteer;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Branch;
import org.resala.Models.Volunteer.UserType;
import org.resala.Repository.Volunteer.UserTypeRepo;
import org.resala.Service.IssTokenService;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTypeService {
    @Autowired
    private UserTypeRepo userTypeRepo;
    public UserType getById(int id) {
        Optional<UserType> optional = userTypeRepo.findById(id);
        if (!optional.isPresent())
            throw new MyEntityNotFoundException("UserType "+ StaticNames.notFound);
        return optional.get();
    }
}
