package org.resala.Controllers.Event;


import org.resala.Controllers.AuthorizeController;
import org.resala.Controllers.CommonActiveBranchStateController;
import org.resala.Controllers.CommonBranchController;
import org.resala.Controllers.CommonController;
import org.resala.Models.Auth.Response;
import org.resala.Service.Event.EventService;
import org.resala.StaticNames;
import org.resala.dto.Event.EventDTO;
import org.resala.dto.Event.EventStatuesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/event")
public class EventController implements CommonController<EventDTO> {
    @Autowired
    EventService eventService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllEvents + "') or hasRole('" + StaticNames.getAllEventsByMyBranch + "')")
    @Override
    public ResponseEntity<Object> getAll() {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllEvents, authorities))
            return ResponseEntity.ok(new Response(eventService.getAll(), HttpStatus.OK.value()));
        else {
            int branchId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
            return ResponseEntity.ok(new Response(eventService.getEventsByBranchId(branchId), HttpStatus.OK.value()));
        }
    }

    @RequestMapping(value = "/getAllByState", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllEventsByState + "') or hasRole('" + StaticNames.getAllShareableEventsByState + "' ) " +
            "or hasRole('" + StaticNames.getAllEventsByStateAndMyBranch + "' ) or hasRole('" + StaticNames.getAllShareableEventsByStateAndMyBranch + "' )")
    public ResponseEntity<Object> getAllByState(@RequestBody EventStatuesDTO eventStatuesDTO) {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllEventsByState, authorities))
            return ResponseEntity.ok(new Response(eventService.getAllEventsByStateId(eventStatuesDTO.getId()), HttpStatus.OK.value()));
        else if (AuthorizeController.contain(StaticNames.getAllShareableEventsByState, authorities))
            return ResponseEntity.ok(new Response(eventService.getAllEventsByShareableAndEventState(true, eventStatuesDTO.getId()), HttpStatus.OK.value()));
        int branchId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        if (AuthorizeController.contain(StaticNames.getAllEventsByStateAndMyBranch, authorities))
            return ResponseEntity.ok(new Response(eventService.getAllByStateIdAndBranchId(eventStatuesDTO.getId(), branchId), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(eventService.getAllEventsByShareableAndBranchIdAndEventStateId(true, branchId, eventStatuesDTO.getId()), HttpStatus.OK.value()));

    }

    /*@RequestMapping(value = "/getAllActiveEvents", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveEvents + "')")
    public ResponseEntity<Object> getAllActive() {
        return ResponseEntity.ok(new Response(eventService.getAllEventsByStateName(StaticNames.activeState), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllCompletedEvents", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllCompletedEvents + "')")
    public ResponseEntity<Object> getAllCompleted() {
        return ResponseEntity.ok(new Response(eventService.getAllEventsByStateName(StaticNames.completedState), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getAllActiveShareableEvents", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveShareableEvents + "')")
    public ResponseEntity<Object> getAllActiveShareable() {
        return ResponseEntity.ok(new Response(eventService.getAllEventsByShareableAndEventState(true, StaticNames.activeState), HttpStatus.OK.value()));
    }*/

    @RequestMapping(value = "/getAllByState/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllEventsByState + "') or hasRole('" + StaticNames.getAllShareableEventsByState + "' )")
    public ResponseEntity<Object> getAllByStateAndBranchId(@RequestBody EventStatuesDTO eventStatuesDTO,@PathVariable int branchId) {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllEventsByState, authorities)){

            return ResponseEntity.ok(new Response(eventService.getAllByStateIdAndBranchId(eventStatuesDTO.getId(),branchId), HttpStatus.OK.value()));

        }
        else{
            System.out.println("ZzzZZZzzzzzz");
            return ResponseEntity.ok(new Response(eventService.getAllEventsByShareableAndBranchIdAndEventStateId(true, branchId,eventStatuesDTO.getId()), HttpStatus.OK.value()));

        }
    }

    /*@RequestMapping(value = "/getAllActiveShareableEventsByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveShareableEventsByMyBranchId + "')")
    public ResponseEntity<Object> getAllActiveShareableByMyBranchId() {
        int branchId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        return ResponseEntity.ok(new Response(eventService.getAllEventsByShareableAndBranchIdAndEventState(true, branchId, StaticNames.activeState), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getAllCompletedShareableEvents", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllCompletedShareableEvents + "')")
    public ResponseEntity<Object> getAllCompletedShareable() {
        return ResponseEntity.ok(new Response(eventService.getAllEventsByShareableAndEventState(true, StaticNames.completedState), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllCompletedShareableEventsByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllCompletedShareableEvents + "')")
    public ResponseEntity<Object> getAllCompletedShareableByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(eventService.getAllEventsByShareableAndBranchIdAndEventState(true, branchId, StaticNames.completedState), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllCompletedShareableEventsByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllCompletedShareableEventsByMyBranchId + "')")
    public ResponseEntity<Object> getAllCompletedShareableByMyBranchId() {
        int branchId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        return ResponseEntity.ok(new Response(eventService.getAllEventsByShareableAndBranchIdAndEventState(true, branchId, StaticNames.completedState), HttpStatus.OK.value()));
    }*/


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
    public ResponseEntity<Object> getAllByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(eventService.getEventsByBranchId(branchId), HttpStatus.OK.value()));
    }

   /* @RequestMapping(value = "/getEventsByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllEventsByMyBranch + "')")
    @Override
    public ResponseEntity<Object> getAllByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(eventService.getEventsByBranchId(Integer.parseInt(branchId)), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getActiveEventsByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveEvents + "')")
    @Override
    public ResponseEntity<Object> getAllActiveByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(eventService.getAllEventByStateAndBranchId(StaticNames.activeState, branchId), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getActiveEventsByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveEventsByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllActiveByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(eventService.getAllEventByStateAndBranchId(StaticNames.activeState, Integer.parseInt(branchId)), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getArchivedEventsByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedEvents + "')")
    @Override
    public ResponseEntity<Object> getAllArchivedByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(eventService.getAllEventByStateAndBranchId(StaticNames.archivedState, branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getArchivedEventsByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedEventsByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllArchivedByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(eventService.getAllEventByStateAndBranchId(StaticNames.archivedState, Integer.parseInt(branchId)), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getCompletedEventsByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllCompletedEvents + "')")
    public ResponseEntity<Object> getAllCompletedByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(eventService.getAllEventByStateAndBranchId(StaticNames.completedState, branchId), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getCompletedEventsByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllCompletedEventsByMyBranchId + "')")
    public ResponseEntity<Object> getAllCompletedByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(eventService.getAllEventByStateAndBranchId(StaticNames.completedState, Integer.parseInt(branchId)), HttpStatus.OK.value()));
    }*/


}
