package org.resala.Controllers.KPI;

import org.resala.Models.Auth.Response;
import org.resala.Service.KPI.LeadVolunteerKPIService;
import org.resala.Service.KPI.VolunteerKPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leadVolunteerKPI")
public class LeadVolunteerKPIController {
    @Autowired
    LeadVolunteerKPIService leadVolunteerKPIService;

    @RequestMapping(value = "/generateKPIs", method = RequestMethod.GET)
    public ResponseEntity<Object> generateKPIs() {
        leadVolunteerKPIService.generateKPIsForAll();
        return ResponseEntity.ok(new Response("KPIs Generated Successfully", HttpStatus.OK.value()));

    }

}

