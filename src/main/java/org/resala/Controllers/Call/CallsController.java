package org.resala.Controllers.Call;

import org.resala.Service.Call.CallsService;
import org.resala.StaticNames;
import org.resala.dto.Call.SubmitCallDTO;
import org.resala.dto.Volunteer.NetworkAssignedToVolunteersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calls")
public class CallsController {
    @Autowired
    CallsService callsService;


    @RequestMapping(value = "/confirmAssignedCalls/{balanced}" , method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.assignCalls + "')")
    public ResponseEntity<Object> confirmAssignCalls(@PathVariable boolean balanced, @RequestBody NetworkAssignedToVolunteersDTO networkAssignedToVolunteersDTO){

        return callsService.confirmAssignedCalls(balanced, networkAssignedToVolunteersDTO);
    }

    @RequestMapping(value = "/getAssignedCalls",method = RequestMethod.POST)
    @PreAuthorize("hasRole('"+StaticNames.getAssignedCalls+"')")
    public ResponseEntity<Object> getEventAssignCalls(@RequestBody NetworkAssignedToVolunteersDTO dto){
        return callsService.getAssignedCalls(dto);
    }


    @RequestMapping(value = "/submitAssignedCalls",method = RequestMethod.POST)
    @PreAuthorize("hasRole('"+StaticNames.submitAssignedCalls+"')")
    public ResponseEntity<Object> submitAssignedCalls(@RequestBody SubmitCallDTO submitCallDTO){

        return callsService.submitAssignedCalls(submitCallDTO);
    }

}
