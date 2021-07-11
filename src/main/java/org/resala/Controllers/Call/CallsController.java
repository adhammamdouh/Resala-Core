package org.resala.Controllers.Call;

import org.resala.Models.Call.CallResult;
import org.resala.Service.Call.CallsService;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.StaticNames;
import org.resala.dto.Call.CallTypeDTO;
import org.resala.dto.Call.NetworkTypeDTO;
import org.resala.dto.Call.SubmitCallDTO;
import org.resala.dto.Event.EventDTO;
import org.resala.dto.Volunteer.NetworkTypeAssignedToVolunteersToEventDTO;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.resala.dto.Call.VolunteerToCallsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/calls")
public class CallsController {
    @Autowired
    CallsService callsService;
    @Autowired
    VolunteerService volunteerService;

    @RequestMapping(value = "/assignCalls" , method = RequestMethod.POST)/////
    @PreAuthorize("hasRole('" + StaticNames.assignCalls + "')")
    public ResponseEntity<Object> assignCalls(@RequestBody VolunteerToCallsDTO volunteerToCallsDTO){

        return callsService.assignCalls(volunteerToCallsDTO);
    }


    @RequestMapping(value = "/confirmAssignedCalls/{balanced}" , method = RequestMethod.POST)/////
    @PreAuthorize("hasRole('" + StaticNames.assignCalls + "')")
    public ResponseEntity<Object> confirmAssignCalls(@PathVariable boolean balanced, @RequestBody NetworkTypeAssignedToVolunteersToEventDTO networkTypeAssignedToVolunteersToEventDTO){

        return callsService.confirmAssignedCalls(balanced, networkTypeAssignedToVolunteersToEventDTO);
    }

    @RequestMapping(value = "/getAssignedCalls",method = RequestMethod.GET)
    @PreAuthorize("hasRole('"+StaticNames.getAssignedCalls+"')")
    public ResponseEntity<Object> getEventAssignCalls(@RequestBody VolunteerToCallsDTO volunteerToCallsDTO){
        return ResponseEntity.ok(callsService.getAssignedCalls(volunteerToCallsDTO));
    }


    @RequestMapping(value = "/submitAssignedCalls",method = RequestMethod.POST)
    @PreAuthorize("hasRole('"+StaticNames.submitAssignedCalls+"')")
    public ResponseEntity<Object> submitAssignedCalls(@RequestBody SubmitCallDTO submitCallDTO){

        return callsService.submitAssignedCalls(submitCallDTO);
    }

}
