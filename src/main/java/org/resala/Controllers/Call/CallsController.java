package org.resala.Controllers.Call;

import org.resala.Models.Call.CallResult;
import org.resala.Service.Call.CallsService;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.StaticNames;
import org.resala.dto.Call.CallTypeDTO;
import org.resala.dto.Call.NetworkTypeDTO;
import org.resala.dto.Call.SubmitCallDTO;
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

    @RequestMapping(value = "/assignCalls/{equality}" , method = RequestMethod.POST)/////
    @PreAuthorize("hasRole('" + StaticNames.assignCalls + "')")
    public ResponseEntity<Object> assignCalls(@PathVariable boolean equality, @RequestBody VolunteerToCallsDTO data){
        int branchId= Integer.parseInt(
                SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        List<VolunteerDTO> volunteers = new ArrayList<>();
        List<NetworkTypeDTO>  calls = new ArrayList<>();

        volunteers=data.getVolunteers();
        calls=data.getNetworkTypes();

        return ResponseEntity.ok(callsService.assignCalls(branchId,equality,data));
    }

    @RequestMapping(value = "/getAssignedCalls",method = RequestMethod.GET)
    @PreAuthorize("hasRole('"+StaticNames.getAssignedCalls+"')")
    public ResponseEntity<Object> getEventAssignCalls(@RequestBody VolunteerToCallsDTO data){
        VolunteerDTO volunteerDTO = data.getVolunteers().get(0);
        CallTypeDTO callTypeDTO = data.getCallType();
        return ResponseEntity.ok(callsService.getAssignedCalls(volunteerDTO,callTypeDTO));
    }


    @RequestMapping(value = "/submitAssignedCalls",method = RequestMethod.GET)
    @PreAuthorize("hasRole('"+StaticNames.submitAssignedCalls+"')")
    public ResponseEntity<Object> submitAssignedCalls(@RequestBody SubmitCallDTO submitCallDTO){

        int callId=submitCallDTO.getCallId();
        CallTypeDTO callTypeDTO=submitCallDTO.getCallTypeDTO();
        String comment = submitCallDTO.getComment();
        CallResult callResult = submitCallDTO.getCallResult();

        callsService.submitAssignedCalls(callId,callTypeDTO,comment,callResult);
        return ResponseEntity.ok("calls submitted successfully");
    }

}
