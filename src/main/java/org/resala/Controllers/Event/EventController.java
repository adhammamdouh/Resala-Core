package org.resala.Controllers.Event;


import org.resala.Controllers.AuthorizeController;
import org.resala.Controllers.CommonController;
import org.resala.Models.Auth.Response;
import org.resala.Service.Event.EventService;
import org.resala.Service.TokenService;
import org.resala.StaticNames;
import org.resala.dto.Event.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

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
            return ResponseEntity.ok(new Response(eventService.getEventsByBranchId(TokenService.getBranchId()), HttpStatus.OK.value()));
        }
    }

    @RequestMapping(value = "/getAllByState/{eventStatusId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllEventsByState + "') or hasRole('" + StaticNames.getAllShareableEventsByState + "' ) " +
            "or hasRole('" + StaticNames.getAllEventsByStateAndMyBranch + "' ) or hasRole('" + StaticNames.getAllShareableEventsByStateAndMyBranch + "' )")
    public ResponseEntity<Object> getAllByState(@PathVariable int eventStatusId) {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllEventsByState, authorities))
            return ResponseEntity.ok(new Response(eventService.getAllEventsByStateId(eventStatusId), HttpStatus.OK.value()));
        else if (AuthorizeController.contain(StaticNames.getAllShareableEventsByState, authorities))
            return ResponseEntity.ok(new Response(eventService.getAllEventsByShareableAndEventState(true, eventStatusId), HttpStatus.OK.value()));
        if (AuthorizeController.contain(StaticNames.getAllEventsByStateAndMyBranch, authorities))
            return ResponseEntity.ok(new Response(eventService.getAllByStateIdAndBranchId(eventStatusId, TokenService.getBranchId()), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(eventService.getAllEventsByShareableAndBranchIdAndEventStateId(true, TokenService.getBranchId(), eventStatusId), HttpStatus.OK.value()));

    }



    @RequestMapping(value = "/getAllByState/{eventStatusId}/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllEventsByState + "') or hasRole('" + StaticNames.getAllShareableEventsByState + "' )")
    public ResponseEntity<Object> getAllByStateAndBranchId(@PathVariable int eventStatusId,@PathVariable int branchId) {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllEventsByState, authorities)){

            return ResponseEntity.ok(new Response(eventService.getAllByStateIdAndBranchId(eventStatusId,branchId), HttpStatus.OK.value()));

        }
        else{
            return ResponseEntity.ok(new Response(eventService.getAllEventsByShareableAndBranchIdAndEventStateId(true, branchId,eventStatusId), HttpStatus.OK.value()));

        }
    }



    @RequestMapping(value = "/addEvent", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.addEvent + "')")
    @Override
    public ResponseEntity<Object> add(@RequestBody List<EventDTO> obj) {
        return eventService.create(obj);
    }

    @RequestMapping(value = "/archiveEvent", method = RequestMethod.POST)
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


}
