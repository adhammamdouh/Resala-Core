package org.resala.Controllers.Branch;

import org.resala.Models.Auth.Response;
import org.resala.Service.BranchService;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/branch")
public class BranchController {
    @Autowired
    BranchService branchService;

    @RequestMapping(value = "/getAllBranches",method = RequestMethod.GET)
    @PreAuthorize("hasRole('"+ StaticNames.getAllBranches +"')")
    public ResponseEntity<Object> getAllBranches(){
        return ResponseEntity.ok(new Response(branchService.getAll(), HttpStatus.OK.value()));
    }


}
