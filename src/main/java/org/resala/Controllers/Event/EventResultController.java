package org.resala.Controllers.Event;

import org.resala.Models.Auth.Response;
import org.resala.Models.Event.EventResult;
import org.resala.Service.Event.EventResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventResult")
public class EventResultController {
    @Autowired
    EventResultService eventResultSerivice;
    @RequestMapping(value = "/generateKPIs", method = RequestMethod.POST)
    public ResponseEntity<Object> generateKPIs() {
        eventResultSerivice.generateKPIsForAll();
        return ResponseEntity.ok(new Response("KPIs Generated Successfully", HttpStatus.OK.value()));

    }
}
