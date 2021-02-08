package org.resala.Controllers.Event;


import org.resala.Controllers.CommonBranchController;
import org.resala.Controllers.CommonController;
import org.resala.Models.Auth.Response;
import org.resala.Service.Event.EventService;
import org.resala.StaticNames;
import org.resala.dto.Event.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController implements CommonController<EventDTO>, CommonBranchController {
    @Autowired
    EventService eventService;

    @RequestMapping(value = "/getAllEvents", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllEvents + "')")
    @Override
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(new Response(eventService.getAll(), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "addEvent", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.addEvent + "')")
    @Override
    public ResponseEntity<Object> add(@RequestBody EventDTO obj) {
        return eventService.create(obj);
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
        return ResponseEntity.ok(new Response(eventService.getEventsByBranchId(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getEventsByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getEventsByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(eventService.getEventsByBranchId(Integer.parseInt(branchId)), HttpStatus.OK.value()));
    }
}
