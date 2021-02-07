package org.resala.resala.Controllers.Event;


import org.resala.Controllers.CommonBranchController;
import org.resala.Controllers.CommonController;
import org.resala.Service.Event.EventService;
import org.resala.StaticNames;
import org.resala.dto.Event.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController implements CommonController<EventDTO>, CommonBranchController {
    @Autowired
    EventService eventService;

    @RequestMapping(value = "/getAllEvents", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllEvents + "')")
    @Override
    public ResponseEntity<Object> getAll() {
        return eventService.getAllEvents();
    }

    @Override
    public ResponseEntity<Object> insert(EventDTO obj) {
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

    @RequestMapping(value = "/getEventsByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllEvents + "')")
    @Override
    public ResponseEntity<Object> getByBranchId(@PathVariable int branchId) {
        return eventService.getEventsByBranchId(branchId);
    }

    @RequestMapping(value = "/getEventsByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getEventsByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return eventService.getEventsByBranchId(Integer.parseInt(branchId));
    }
}
