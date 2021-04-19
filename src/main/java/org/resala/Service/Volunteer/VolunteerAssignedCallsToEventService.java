package org.resala.Service.Volunteer;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Auth.Response;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.Role;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Models.Volunteer.VolunteerAssignedCallsToEvent;
import org.resala.Repository.Volunteer.VolunteerAssignedCallsToEventRepo;
import org.resala.Service.Call.NetworkTypeService;
import org.resala.Service.CommonCRUDService;
import org.resala.Service.CommonService;
import org.resala.Service.Event.EventService;
import org.resala.StaticNames;
import org.resala.dto.Call.NetworkTypeDTO;
import org.resala.dto.Event.EventDTO;
import org.resala.dto.Volunteer.RoleDTO;
import org.resala.dto.Volunteer.VolunteerAssignedCallsToEventDTO;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VolunteerAssignedCallsToEventService implements CommonService<VolunteerAssignedCallsToEvent> {

    @Autowired
    VolunteerAssignedCallsToEventRepo volunteerAssignedCallsToEventRepo;

    @Autowired
    NetworkTypeService networkTypeService;

    @Autowired
    VolunteerService volunteerService;

    @Autowired
    EventService eventService;


    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    public  <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> modelMapper().map(entity, outCLass))
                .collect(Collectors.toList());
    }



    public ResponseEntity<Object> create(VolunteerAssignedCallsToEventDTO dto) {
        dto.checkNull();
        VolunteerAssignedCallsToEvent volunteerAssignedCallsToEvent = modelMapper().map(dto,VolunteerAssignedCallsToEvent.class);

        volunteerAssignedCallsToEventRepo.save(volunteerAssignedCallsToEvent);
        return ResponseEntity.ok(new Response(StaticNames.addedSuccessfully, HttpStatus.OK.value()));
    }


    @Override
    public VolunteerAssignedCallsToEvent getById(int id) {
        Optional<VolunteerAssignedCallsToEvent> volunteerAssignedCallsToEvent=
                volunteerAssignedCallsToEventRepo.getById(id);
        if(!volunteerAssignedCallsToEvent.isPresent())
            throw new MyEntityNotFoundException("assigned calls " + StaticNames.notFound);

        return volunteerAssignedCallsToEvent.get();
    }

    @Override
    public List<VolunteerAssignedCallsToEvent> getAll() {
        return null;
    }

    public VolunteerAssignedCallsToEvent getByVolunteerAndEvent(int volunteerId, int eventId){
        Optional<VolunteerAssignedCallsToEvent> volunteerAssignedCallsToEvent=
                volunteerAssignedCallsToEventRepo.getByVolunteer_IdAndEvent_Id(volunteerId,eventId);
        if(!volunteerAssignedCallsToEvent.isPresent()) return null;
        return volunteerAssignedCallsToEvent.get();
    }

    public void update(VolunteerDTO volunteerDto, EventDTO eventDto, List<NetworkTypeDTO> networkType){
        Volunteer volunteer= volunteerService.getById(volunteerDto.getId());
        Event event = eventService.getById(eventDto.getId());

        System.out.println("id = " + event.getId());

        VolunteerAssignedCallsToEvent volunteerAssignedCallsToEvent =
                getByVolunteerAndEvent(volunteer.getId(),event.getId());

        if(volunteerAssignedCallsToEvent!= null){
            volunteerAssignedCallsToEvent.setNetworkTypeList(mapAll(networkType,NetworkType.class));
            volunteerAssignedCallsToEventRepo.save(volunteerAssignedCallsToEvent);
        }
        else {
            create(new VolunteerAssignedCallsToEventDTO(networkType,volunteerDto,eventDto));
        }
    }

    public List<VolunteerAssignedCallsToEvent> getByEventId(int eventId){
        System.out.println("id   " +eventId);

        List<VolunteerAssignedCallsToEvent> volunteerAssignedCallsToEvents=
                volunteerAssignedCallsToEventRepo.getByEvent_Id(eventId);

        if(volunteerAssignedCallsToEvents.isEmpty() || volunteerAssignedCallsToEvents==null)
            throw new MyEntityNotFoundException("Calls" +StaticNames.notFound);

        return volunteerAssignedCallsToEvents;
    }
}
