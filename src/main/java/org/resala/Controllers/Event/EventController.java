package org.resala.Controllers.Event;


import org.resala.Controllers.CommonActiveBranchStateController;
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
public class EventController implements CommonController<EventDTO>, CommonBranchController, CommonActiveBranchStateController {
    @Autowired
    EventService eventService;

    @RequestMapping(value = "/getAllEvents", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllEvents + "')")
    @Override
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(new Response(eventService.getAll(), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllArchivedEvents", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedEvents + "')")
    public ResponseEntity<Object> getAllArchived() {
        return ResponseEntity.ok(new Response(eventService.getAllArchivedEvent(), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllActiveEvents", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveEvents + "')")
    public ResponseEntity<Object> getAllActive() {
        return ResponseEntity.ok(new Response(eventService.getAllActiveEvent(), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllCompletedEvents", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllCompletedEvents + "')")
    public ResponseEntity<Object> getAllCompleted() {
        return ResponseEntity.ok(new Response(eventService.getAllCompletedEvent(), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/addEvent", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.addEvent + "')")
    @Override
    public ResponseEntity<Object> add(@RequestBody EventDTO obj) {
        return eventService.create(obj);
    }

    @RequestMapping(value = "/archiveEvent", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('" + StaticNames.archiveEvent + "')")
    @Override
    public ResponseEntity<Object> archive(@RequestBody EventDTO obj) {
        return eventService.archive(obj);
    }

    @RequestMapping(value = "/completeEvent", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('" + StaticNames.completeEvent + "')")
    public ResponseEntity<Object> complete(@RequestBody EventDTO obj) {
        return eventService.complete(obj);
    }

    @RequestMapping(value = "/updateEvent", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('" + StaticNames.updateEvent + "')")
    @Override
    public ResponseEntity<Object> update(@RequestBody EventDTO newObj) {
        return eventService.update(newObj);
    }

    @RequestMapping(value = "/getEventsByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllEvents + "')")
    @Override
    public ResponseEntity<Object> getAllByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(eventService.getEventsByBranchId(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getEventsByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getEventsByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(eventService.getEventsByBranchId(Integer.parseInt(branchId)), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getActiveEventsByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveEvents + "')")
    @Override
    public ResponseEntity<Object> getAllActiveByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(eventService.getAllActiveEventByBranchId(branchId), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getActiveEventsByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveEventsByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllActiveByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(eventService.getAllActiveEventByBranchId(Integer.parseInt(branchId)), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getArchivedEventsByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedEvents + "')")
    @Override
    public ResponseEntity<Object> getAllArchivedByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(eventService.getAllArchivedEventByBranchId(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getArchivedEventsByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedEventsByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllArchivedByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(eventService.getAllArchivedEventByBranchId(Integer.parseInt(branchId)), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getCompletedEventsByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllCompletedEvents + "')")
    public ResponseEntity<Object> getAllCompletedByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(eventService.getAllCompletedEventByBranchId(branchId), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getCompletedEventsByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllCompletedEventsByMyBranchId + "')")
    public ResponseEntity<Object> getAllCompletedByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(eventService.getAllCompletedEventByBranchId(Integer.parseInt(branchId)), HttpStatus.OK.value()));
    }


}
