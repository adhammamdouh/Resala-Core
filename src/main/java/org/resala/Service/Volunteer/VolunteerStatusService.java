package org.resala.Service.Volunteer;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Volunteer.VolunteerStatus;
import org.resala.Repository.Volunteer.VolunteerStatuesRepo;
import org.resala.Service.CommonCRUDService;
import org.resala.Service.CommonService;
import org.resala.StaticNames;
import org.resala.dto.Volunteer.VolunteerStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerStatusService implements CommonCRUDService<VolunteerStatusDTO>, CommonService<VolunteerStatus> {
    @Autowired
    VolunteerStatuesRepo volunteerStatuesRepo;
    @Override
    public ResponseEntity<Object> create(VolunteerStatusDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(VolunteerStatusDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(VolunteerStatusDTO newDto) {
        return null;
    }

    @Override
    public VolunteerStatus get(int id) {
        return null;
    }

    @Override
    public List<VolunteerStatus> getAll() {
        return null;
    }

    public VolunteerStatus getVolunteerStatus(String name){
        Optional<VolunteerStatus> volunteerStatusOptional=volunteerStatuesRepo.findByName(name);
        if(!volunteerStatusOptional.isPresent())
            throw  new MyEntityNotFoundException(name+" State "+ StaticNames.notFound);
        return volunteerStatusOptional.get();
    }


    public VolunteerStatus getVolunteerStatusByUserName(String userName){
        Optional<VolunteerStatus> volunteerStatusOptional=volunteerStatuesRepo.findByVolunteers_User_UserName(userName);
        System.out.println("user name is "+userName);
        if(!volunteerStatusOptional.isPresent())
            throw  new MyEntityNotFoundException("User State "+StaticNames.notFound);
        System.out.println("EEEEEEEEEEEEEEEE");
        return volunteerStatusOptional.get();
    }
}
