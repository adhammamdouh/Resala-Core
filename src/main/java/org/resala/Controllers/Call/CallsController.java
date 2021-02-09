package org.resala.Controllers.Call;

import org.resala.Service.Call.CallsService;
import org.resala.StaticNames;
import org.resala.dto.VolunteerToCallsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/calls")
public class CallsController {
    @Autowired
    CallsService callsService;

    @RequestMapping(value = "/assignCalls" , method = RequestMethod.POST)/////
    @PreAuthorize("hasRole('" + StaticNames.assignCalls + "')")
    public ResponseEntity<Object> assignCalls(@RequestBody List<VolunteerToCallsDTO> data){
        int branchId= Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        List<Integer> volunteers = new ArrayList<>();
        List<Integer>  calls = new ArrayList<>();
        for(int i=0;i<data.size();++i){
            volunteers.add(data.get(i).getVolunteerId());
            calls.add(data.get(i).getCallId());
        }
        return ResponseEntity.ok(callsService.assignCalls(volunteers,calls,branchId,false));
    }

    @RequestMapping(value = "/assignCallsEqually" , method = RequestMethod.POST)/////
    @PreAuthorize("hasRole('" + StaticNames.assignCalls + "')")
    public ResponseEntity<Object> assignCallsEqually(@RequestBody List<VolunteerToCallsDTO> data){
        int branchId= Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        List<Integer> volunteers = new ArrayList<>();
        List<Integer>  calls = new ArrayList<>();
        for(int i=0;i<data.size();++i){
            volunteers.add(data.get(i).getVolunteerId());
            calls.add(data.get(i).getCallId());
        }
        return ResponseEntity.ok(callsService.assignCalls(volunteers,calls,branchId,true));
    }
}
