package org.resala.Service.Volunteer;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Auth.Response;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Models.Volunteer.NetworkTypeAssignedToVolunteersToEvent;
import org.resala.Repository.Volunteer.NetworkTypeAssignedToVolunteersToEventRepo;
import org.resala.Service.Call.NetworkTypeService;
import org.resala.Service.CommonService;
import org.resala.Service.Event.EventService;
import org.resala.StaticNames;
import org.resala.dto.Call.NetworkTypeDTO;
import org.resala.dto.Event.EventDTO;
import org.resala.dto.Volunteer.NetworkTypeAssignedToVolunteersToEventDTO;
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
public class NetworkTypeAssignedToVolunteersToEventService implements CommonService<NetworkTypeAssignedToVolunteersToEvent> {

    @Autowired
    NetworkTypeAssignedToVolunteersToEventRepo networkTypeAssignedToVolunteersToEventRepo;

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



    public ResponseEntity<Object> create(NetworkTypeAssignedToVolunteersToEventDTO dto) {
        dto.checkNull();
        NetworkTypeAssignedToVolunteersToEvent networkTypeAssignedToVolunteersToEvent = modelMapper().map(dto, NetworkTypeAssignedToVolunteersToEvent.class);

        networkTypeAssignedToVolunteersToEventRepo.save(networkTypeAssignedToVolunteersToEvent);
        return ResponseEntity.ok(new Response(StaticNames.addedSuccessfully, HttpStatus.OK.value()));
    }


    @Override
    public NetworkTypeAssignedToVolunteersToEvent getById(int id) {
        Optional<NetworkTypeAssignedToVolunteersToEvent> volunteerAssignedCallsToEvent=
                networkTypeAssignedToVolunteersToEventRepo.getById(id);
        if(!volunteerAssignedCallsToEvent.isPresent())
            throw new MyEntityNotFoundException("assigned calls " + StaticNames.notFound);

        return volunteerAssignedCallsToEvent.get();
    }

    @Override
    public List<NetworkTypeAssignedToVolunteersToEvent> getAll() {
        return null;
    }

    public NetworkTypeAssignedToVolunteersToEvent getByNetworkTypeAndEvent(int networkTypeId, int eventId){
        Optional<NetworkTypeAssignedToVolunteersToEvent> networkTypeAssignedToVolunteersToEvent =
                networkTypeAssignedToVolunteersToEventRepo.getByNetworkType_IdAndEvent_Id(networkTypeId,eventId);
        if(!networkTypeAssignedToVolunteersToEvent.isPresent()) return null;
        return networkTypeAssignedToVolunteersToEvent.get();
    }

    public void update(List<VolunteerDTO> volunteersDto, EventDTO eventDto, NetworkTypeDTO networkTypeDto){
        NetworkType networkType = networkTypeService.getById(networkTypeDto.getId());
        Event event = eventService.getById(eventDto.getId());

//        System.out.println("id = " + event.getId());

        NetworkTypeAssignedToVolunteersToEvent networkTypeAssignedToVolunteersToEvent =
                getByNetworkTypeAndEvent(networkType.getId(),event.getId());

        if(networkTypeAssignedToVolunteersToEvent != null){
            for(VolunteerDTO volunteerDto : volunteersDto) {
                Volunteer volunteer = volunteerService.getById(volunteerDto.getId());
            }

            networkTypeAssignedToVolunteersToEvent.setVolunteers(mapAll(volunteersDto,Volunteer.class));
            networkTypeAssignedToVolunteersToEventRepo.save(networkTypeAssignedToVolunteersToEvent);
        }
        else {
            create(new NetworkTypeAssignedToVolunteersToEventDTO(volunteersDto,networkTypeDto,eventDto));
        }
    }

    public List<NetworkTypeAssignedToVolunteersToEvent> getByEventId(int eventId){
        System.out.println("id   " +eventId);

        List<NetworkTypeAssignedToVolunteersToEvent> networkTypeAssignedToVolunteersToEvents =
                networkTypeAssignedToVolunteersToEventRepo.getByEvent_Id(eventId);

        if(networkTypeAssignedToVolunteersToEvents.isEmpty() || networkTypeAssignedToVolunteersToEvents ==null)
            throw new MyEntityNotFoundException("Calls" +StaticNames.notFound);

        return networkTypeAssignedToVolunteersToEvents;
    }
}
