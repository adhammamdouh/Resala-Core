package org.resala.resala.Service.Volunteer;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Volunteer.VolunteerStatus;
import org.resala.Repository.Volunteer.VolunteerStatuesRepo;
import org.resala.Service.CommonService;
import org.resala.dto.Volunteer.VolunteerStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VolunteerStatusService implements CommonService<VolunteerStatusDTO> {
    @Autowired
    VolunteerStatuesRepo volunteerStatuesRepo;
    @Override
    public ResponseEntity<Object> create(VolunteerStatusDTO obj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(VolunteerStatusDTO obj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(VolunteerStatusDTO newObj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> get(int id) {
        return null;
    }
    public VolunteerStatus getVolunteerStatusByName(String name){
        Optional<VolunteerStatus> volunteerStatusOptional=volunteerStatuesRepo.findByName(name);
        if(!volunteerStatusOptional.isPresent())
            throw  new MyEntityNotFoundException(name+" State Not Found");
        return volunteerStatusOptional.get();
    }
}
