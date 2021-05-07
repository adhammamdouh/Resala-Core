package org.resala.Service.Event;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Event.EventStatus;
import org.resala.Models.Volunteer.VolunteerStatus;
import org.resala.Repository.Event.EventStatusRepo;
import org.resala.Repository.Volunteer.VolunteerStatuesRepo;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventStatusService {
    @Autowired
    EventStatusRepo eventStatusRepo;
    public EventStatus getEventStatusByName(String name){
        Optional<EventStatus> eventStatus=eventStatusRepo.findByName(name);
        if(!eventStatus.isPresent())
            throw  new MyEntityNotFoundException(name+" State "+ StaticNames.notFound);
        return eventStatus.get();
    }
    public EventStatus getEventStatusById(int id){
        Optional<EventStatus> eventStatus=eventStatusRepo.findById(id);
        if(!eventStatus.isPresent())
            throw  new MyEntityNotFoundException(id+" State "+ StaticNames.notFound);
        return eventStatus.get();
    }
}
