package org.resala.Service.Event;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.ActiveStateException;
import org.resala.Exceptions.ConstraintViolationException;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Auth.Response;
import org.resala.Models.Branch;
import org.resala.Models.Event.Event;
import org.resala.Models.Event.EventStatus;
import org.resala.Repository.Event.EventRepo;
import org.resala.Service.BranchService;
import org.resala.Service.Call.CallsService;
import org.resala.Service.CheckConstraintService;
import org.resala.Service.CommonCRUDService;
import org.resala.Service.CommonService;
import org.resala.StaticNames;
import org.resala.dto.BranchDTO;
import org.resala.dto.Event.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService implements CommonCRUDService<EventDTO>, CommonService<Event> {
    @Autowired
    private EventRepo eventRepo;
    @Autowired
    private BranchService branchService;
    @Autowired
    private CallsService callsService;
    @Autowired
    private EventStatusService eventStatusService;

    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    @Override
    public ResponseEntity<Object> create(EventDTO dto) {
        dto.checkNull();
        EventStatus eventStatus = eventStatusService.getEventStatus(StaticNames.activeState);
        List<Branch> branches = branchService.getBranchByIds(
                dto.getBranches().stream().map(BranchDTO::getId).collect(Collectors.toList())
        );

        Event event = modelMapper().map(dto, Event.class);
        event.setId(0);
        event.setBranches(branches);
        event.setEventStatus(eventStatus);

        checkConstraintViolations(event);
        eventRepo.save(event);
        return ResponseEntity.ok(new Response(StaticNames.addedSuccessfully, HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> archive(EventDTO dto) {
        Event event = getById(dto.getId());
        if (!event.getEventStatus().getName().equals(StaticNames.activeState))
            throw new ActiveStateException("This Event State is " + event.getEventStatus().getName());
        EventStatus eventStatus = eventStatusService.getEventStatus(StaticNames.archivedState);
        event.setEventStatus(eventStatus);
        eventRepo.save(event);
        return ResponseEntity.ok(new Response(StaticNames.archivedSuccessfully, HttpStatus.OK.value()));
    }

    public ResponseEntity<Object> complete(EventDTO dto) {
        Event event = getById(dto.getId());
        if (!event.getEventStatus().getName().equals(StaticNames.activeState))
            throw new ActiveStateException("This Event State is " + event.getEventStatus().getName());
        EventStatus eventStatus = eventStatusService.getEventStatus(StaticNames.completedState);
        event.setEventStatus(eventStatus);
        eventRepo.save(event);
        return ResponseEntity.ok(new Response(StaticNames.completedSuccessfully, HttpStatus.OK.value()));
    }


    @Override
    public ResponseEntity<Object> update(EventDTO newDto) {
        newDto.checkNull();
        Event event = getById(newDto.getId());
        if (!event.getEventStatus().getName().equals(StaticNames.activeState))
            throw new ActiveStateException("This Event State is " + event.getEventStatus().getName());
        List<Branch> branches = branchService.getBranchByIds(
                newDto.getBranches().stream().map(BranchDTO::getId).collect(Collectors.toList())
        );
        Event newEvent = modelMapper().map(newDto, Event.class);
        newEvent.setId(event.getId());
        newEvent.setBranches(branches);
        newEvent.setEventStatus(event.getEventStatus());
        checkConstraintViolations(newEvent);
        eventRepo.save(newEvent);
        return ResponseEntity.ok(new Response(StaticNames.updatedSuccessfully, HttpStatus.OK.value()));
    }

    @Override
    public Event getById(int id) {
        Optional<Event> optionalEvent = eventRepo.findById(id);
        if (!optionalEvent.isPresent())
            throw new MyEntityNotFoundException("Event " + StaticNames.notFound);
        return optionalEvent.get();
    }

    @Override
    public List<Event> getAll() {
        return eventRepo.findAll();
    }

    public List<Event> getEventsByBranchId(int branchId) {
        return eventRepo.findByBranches_id(branchId);
    }

    public List<Event> getAllEventsByState(String state) {
        return eventRepo.findAllByEventStatus_name(state);
    }

    public List<Event> getAllEventsByShareableAndEventState(boolean shareable, String eventState) {
        return eventRepo.findAllByShareableAndEventStatus_Name(shareable, eventState);
    }

    public List<Event> getAllEventsByShareableAndBranchIdAndEventState(boolean shareable, int branchId, String eventState) {
        return eventRepo.findAllByShareableAndEventStatus_NameAndAndBranches_id(shareable, eventState, branchId);
    }

    public List<Event> getAllArchivedEventByBranchId(int branchId) {
        return eventRepo.findAllByBranches_idAndEventStatus_name(branchId, StaticNames.archivedState);
    }

    public List<Event> getAllEventByStateAndBranchId(String state,int branchId) {
        return eventRepo.findAllByBranches_idAndEventStatus_name(branchId, state);
    }

    public List<Event> getAllCompletedEventByBranchId(int branchId) {
        return eventRepo.findAllByBranches_idAndEventStatus_name(branchId, StaticNames.completedState);
    }

    public void checkConstraintViolations(Event event) {
        CheckConstraintService.checkConstraintViolations(event, Event.class);
        if (event.isHasCalls() && event.getCallsStartTime() == null)
            throw new ConstraintViolationException("Your event has Calls so you have to enter CallsStartTime");
    }


}
