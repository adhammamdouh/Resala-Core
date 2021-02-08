package org.resala.Service.Event;

import org.modelmapper.ModelMapper;
import org.resala.Models.Auth.Response;
import org.resala.Models.Branch;
import org.resala.Models.Event.Event;
import org.resala.Repository.Event.EventRepo;
import org.resala.Service.BranchService;
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
import java.util.stream.Collectors;

@Service
public class EventService implements CommonCRUDService<EventDTO>, CommonService<Event> {
    @Autowired
    EventRepo eventRepo;
    @Autowired
    BranchService branchService;
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }
    @Override
    public ResponseEntity<Object> create(EventDTO obj) {
        List<Branch>branches=branchService.getBranchByIds(
                obj.getBranches().stream().map(BranchDTO::getId).collect(Collectors.toList())
        );
        Event event = modelMapper().map(obj, Event.class);
        event.setBranches(branches);
        eventRepo.save(event);
        return ResponseEntity.ok(new Response(StaticNames.addedSuccessfully, HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> delete(EventDTO obj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(EventDTO newObj) {
        return null;
    }

    @Override
    public Event get(int id) {
        return null;
    }

    @Override
    public List<Event> getAll() {
        return eventRepo.findAll();
    }

    public List<Event> getEventsByBranchId(int branchId) {
        return eventRepo.findByBranches_id(branchId);
    }
}
