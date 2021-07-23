package org.resala.Controllers.NetworkTypeAssignedToVolunteer;

import org.resala.Models.Auth.Response;
import org.resala.Service.IssTokenService;
import org.resala.Service.Volunteer.NetworkAssignedToVolunteersService;
import org.resala.StaticNames;
import org.resala.dto.Call.VolunteerToCallsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/networkType")
public class NetworkAssignedToVolunteerController {
    @Autowired
    NetworkAssignedToVolunteersService networkAssignedToVolunteersService;

    @RequestMapping(value = "/getNetworkAssignedToVolunteer/{eventId}"  , method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getNetworkTypeAssignedToVolunteer + "')")
    public ResponseEntity<Object> getAssignedCalls(@PathVariable int eventId){
        return ResponseEntity.ok(new Response(networkAssignedToVolunteersService.getNetworkTypeAssignedToVolunteer
                (eventId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/assignNetworkAssignedToVolunteer"  , method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.getNetworkTypeAssignedToVolunteer + "')")
    public ResponseEntity<Object> assignedCalls(@RequestBody VolunteerToCallsDTO dto){
        int branchId = IssTokenService.getBranchId();
        return networkAssignedToVolunteersService.saveAndUpdate(dto,branchId);
    }


}
