package org.resala.Controllers.Committee;

import org.resala.Models.Auth.Response;
import org.resala.Service.Commiittee.CommitteeService;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/committee")
public class CommitteeController {
    @Autowired
    CommitteeService committeeService;

    @RequestMapping(value = "/getAllCommittees",method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllCommittees +"')")
    public ResponseEntity<Object> getAllCommittees(){
        return ResponseEntity.ok(new Response(committeeService.getAll(), HttpStatus.OK.value()));
    }
}
