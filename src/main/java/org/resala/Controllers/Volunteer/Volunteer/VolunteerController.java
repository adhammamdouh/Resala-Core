package org.resala.Controllers.Volunteer.Volunteer;


import org.resala.Controllers.CommonBranchController;
import org.resala.Controllers.CommonController;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.StaticNames;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController implements CommonController<VolunteerDTO>, CommonBranchController {
    @Autowired
    VolunteerService volunteerService;

    @RequestMapping(value = "/getAllVolunteers", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteers + "')")
    @Override
    public ResponseEntity<Object> getAll() {
        return volunteerService.getAllVolunteers();
    }

    @RequestMapping(value = "/getVolunteersByBranch/{branchId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.getVolunteersByBranchId + "')")
    @Override
    public ResponseEntity<Object> getByBranchId(@PathVariable int branchId) {
        return volunteerService.getVolunteersByBranch(branchId);
    }

    @RequestMapping(value = "/getVolunteersByBranch", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.getVolunteersByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getByMyBranchId(@PathVariable int branchId) {

        return volunteerService.getVolunteersByBranch(branchId);
    }

    @Override
    public ResponseEntity<Object> insert(VolunteerDTO obj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(VolunteerDTO obj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(VolunteerDTO oldObj, VolunteerDTO newObj) {
        return null;
    }


}
