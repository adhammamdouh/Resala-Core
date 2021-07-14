package org.resala.Controllers.NetworkTypeAssignedToVolunteer;

import org.resala.Models.Auth.Response;
import org.resala.Service.Volunteer.NetworkTypeAssignedToVolunteersToEventService;
import org.resala.StaticNames;
import org.resala.dto.Volunteer.NetworkTypeAssignedToVolunteersToEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping(value = "/networkTypeAssignedToVolunteers")
public class NetworkTypeAssignedToVolunteerController {
    @Autowired
    NetworkTypeAssignedToVolunteersToEventService networkTypeAssignedToVolunteersToEventService;

    @RequestMapping(value = "/getNetworkTypeAssignedToVolunteer"  , method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.getNetworkTypeAssignedToVolunteer + "')")
    public ResponseEntity<Object> getAssignedCalls(@RequestBody NetworkTypeAssignedToVolunteersToEventDTO networkTypeAssignedToVolunteersToEventDTO){
        return ResponseEntity.ok(new Response(networkTypeAssignedToVolunteersToEventService.getNetworkTypeAssignedToVolunteers(networkTypeAssignedToVolunteersToEventDTO), HttpStatus.OK.value()));
    }
}
