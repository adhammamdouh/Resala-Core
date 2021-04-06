package org.resala.Service.Volunteer;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Volunteer.VolunteerStatus;
import org.resala.Repository.Volunteer.VolunteerStatuesRepo;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerStatusService{
    @Autowired
    VolunteerStatuesRepo volunteerStatuesRepo;
    public VolunteerStatus getVolunteerStatus(String name){
        Optional<VolunteerStatus> volunteerStatusOptional=volunteerStatuesRepo.findByName(name);
        if(!volunteerStatusOptional.isPresent())
            throw  new MyEntityNotFoundException(name+" State "+ StaticNames.notFound);
        return volunteerStatusOptional.get();
    }


    public VolunteerStatus getVolunteerStatusByUserName(String userName){
        Optional<VolunteerStatus> volunteerStatusOptional=volunteerStatuesRepo.findByVolunteers_User_UserName(userName);
        if(!volunteerStatusOptional.isPresent())
            throw  new MyEntityNotFoundException("User State "+StaticNames.notFound);
        return volunteerStatusOptional.get();
    }
}
