package org.resala.Controllers.KPI;

import org.resala.Service.KPI.LeadVolunteerKPIService;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leadVolunteerKPI")
public class LeadVolunteerKPIController {
    @Autowired
    LeadVolunteerKPIService leadVolunteerKPIService;

    @RequestMapping(value = "/generateKPIs", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.generateLeadVolunteersKPIs + "')")
    public ResponseEntity<Object> generateKPIs() {
        return leadVolunteerKPIService.generateKPIsForAll();

    }

}

