package org.resala.resala.Service.Event;

import org.resala.Repository.Event.EventRepo;
import org.resala.Service.CommonService;
import org.resala.dto.Event.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EventService implements CommonService<EventDTO> {
    @Autowired
    EventRepo eventRepo;

    @Override
    public ResponseEntity<Object> create(EventDTO obj) {
        return null;
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
    public ResponseEntity<Object> get(int id) {
        return null;
    }

    public ResponseEntity<Object> getEventsByBranchId(int branchId) {
        return ResponseEntity.ok(eventRepo.findByBranches_id(branchId));
    }

    public ResponseEntity<Object> getAllEvents() {
        return ResponseEntity.ok(eventRepo.findAll());
    }

}
