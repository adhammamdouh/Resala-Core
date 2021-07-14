package org.resala.Controllers.KPI;

import org.resala.Models.Auth.Response;
import org.resala.Service.KPI.VolunteerKPIService;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/volunteerKPI")
public class VolunteerKPIController {
    @Autowired
    VolunteerKPIService volunteerKPIService;
    @RequestMapping(value = "/generateKPIs", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.generateVolunteersKPIs + "')")
    public ResponseEntity<Object> generateKPIs() {
        volunteerKPIService.generateKPIsForAll();
        return ResponseEntity.ok(new Response("KPIs Generated Successfully", HttpStatus.OK.value()));

    }
}
