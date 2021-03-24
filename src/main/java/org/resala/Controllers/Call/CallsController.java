package org.resala.Controllers.Call;

import org.resala.Models.Volunteer.Volunteer;
import org.resala.Service.Call.CallsService;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.StaticNames;
import org.resala.dto.Call.CallTypeDTO;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.resala.dto.VolunteerToCallsDTO;
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

    @RequestMapping(value = "/assignCalls/{equality}" , method = RequestMethod.POST)/////
    @PreAuthorize("hasRole('" + StaticNames.assignCalls + "')")
    public ResponseEntity<Object> assignCalls(@PathVariable boolean equality, @RequestBody VolunteerToCallsDTO data){
        int branchId= Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        List<VolunteerDTO> volunteers = new ArrayList<>();
        List<CallTypeDTO>  calls = new ArrayList<>();

        volunteers=data.getVolunteerIds();
        calls=data.getCallTypeIds();

        return ResponseEntity.ok(callsService.assignCalls(volunteers,calls,branchId,equality));
    }

    @RequestMapping(value = "/getAssignedCalls",method = RequestMethod.GET)
    @PreAuthorize("hasRole('"+StaticNames.getAssignedCalls+"')")
    public ResponseEntity<Object> getAssignCalls(@RequestBody VolunteerDTO volunteerDTO){
        return ResponseEntity.ok(callsService.getAssignedCalls(volunteerDTO));
    }

}
