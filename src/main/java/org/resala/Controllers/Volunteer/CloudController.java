package org.resala.Controllers.Volunteer;

import org.resala.Service.Volunteer.CloudService;
import org.resala.StaticNames;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cloud")
public class CloudController {
    @Autowired
    CloudService cloudService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.createCloud + "')")
    public ResponseEntity<Object> add() {
        return cloudService.create();
    }
}
