package org.resala.resala.Controllers.Volunteer;


import org.resala.Controllers.CommonBranchController;
import org.resala.Controllers.CommonController;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.StaticNames;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController implements CommonController<VolunteerDTO>, CommonBranchController {
    @Autowired
    VolunteerService volunteerService;

    @RequestMapping(value = "/getAllVolunteers", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteers + "')")
    @Override
    public ResponseEntity<Object> getAll() {
        return volunteerService.getAllVolunteers();
    }

    @RequestMapping(value = "/getVolunteersByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteers + "')")
    @Override
    public ResponseEntity<Object> getByBranchId(@PathVariable int branchId) {
        return volunteerService.getVolunteersByBranch(branchId);
    }

    @RequestMapping(value = "/getVolunteersByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getVolunteersByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();

        return volunteerService.getVolunteersByBranch(Integer.parseInt(branchId));
    }

    @Override
    @RequestMapping(value = "/createVolunteer", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.createVolunteer + "')")
    public ResponseEntity<Object> insert(@RequestBody VolunteerDTO volunteerDTO) {
        return volunteerService.create(volunteerDTO);
    }

    @Override
    @RequestMapping(value = "/deleteVolunteer", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('" + StaticNames.deleteVolunteer + "')")
    public ResponseEntity<Object> delete(@RequestBody VolunteerDTO obj) {
        return volunteerService.delete(obj);
    }

    @Override
    @RequestMapping(value = "/updateVolunteer", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('" + StaticNames.updateVolunteer + "')")
    public ResponseEntity<Object> update(@RequestBody VolunteerDTO newObj) {
        return volunteerService.update(newObj);
    }


}
