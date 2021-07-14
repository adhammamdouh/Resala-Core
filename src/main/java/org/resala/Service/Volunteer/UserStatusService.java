package org.resala.Service.Volunteer;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Volunteer.UserStatus;
import org.resala.Repository.Volunteer.UserStatuesRepo;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserStatusService {
    @Autowired
    UserStatuesRepo userStatuesRepo;
    public UserStatus getByName(String name){
        Optional<UserStatus> volunteerStatusOptional= userStatuesRepo.findByName(name);
        if(!volunteerStatusOptional.isPresent())
            throw  new MyEntityNotFoundException(name+" State "+ StaticNames.notFound);
        return volunteerStatusOptional.get();
    }

    public UserStatus getById(int id){
        Optional<UserStatus> volunteerStatusOptional= userStatuesRepo.findById(id);
        if(!volunteerStatusOptional.isPresent())
            throw  new MyEntityNotFoundException(id+" State "+ StaticNames.notFound);
        return volunteerStatusOptional.get();
    }


    public UserStatus getVolunteerStatusByUserName(String userName){
        Optional<UserStatus> volunteerStatusOptional= userStatuesRepo.findByVolunteers_User_UserName(userName);
        if(!volunteerStatusOptional.isPresent())
            throw  new MyEntityNotFoundException("User State "+StaticNames.notFound);
        return volunteerStatusOptional.get();
    }
    public UserStatus getCloudStatusByUserName(String userName){
        Optional<UserStatus> volunteerStatusOptional= userStatuesRepo.findByClouds_User_UserName(userName);
        if(!volunteerStatusOptional.isPresent())
            throw  new MyEntityNotFoundException("User State "+StaticNames.notFound);
        return volunteerStatusOptional.get();
    }
}
