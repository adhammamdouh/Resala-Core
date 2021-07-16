package org.resala.Controllers.Volunteer;

import org.resala.Service.Volunteer.AdminService;
import org.resala.StaticNames;
import org.resala.dto.Privilege.PrivilegeDTO;
import org.resala.dto.Volunteer.UserDTO;
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
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.createUser + "')")
    public ResponseEntity<Object> add(@RequestBody List<UserDTO> userDTOS) {
        return adminService.createUser(userDTOS);
    }

    @RequestMapping(value = "/assignVolunteerUser", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.assignUser + "')")
    public ResponseEntity<Object> assignVolunteerUser(@RequestBody List<VolunteerDTO> volunteerDTOS) {
        return adminService.assignVolunteerUser(volunteerDTOS);
    }
    @RequestMapping(value = "/addPrivileges", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.assignUser + "')")
    public ResponseEntity<Object> addPrivileges(@RequestBody List<PrivilegeDTO> privilegeDTOS) {
        return adminService.addPrivileges(privilegeDTOS);
    }

    @RequestMapping(value = "/addPrivilegesActions", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.assignUser + "')")
    public ResponseEntity<Object> addPrivilegesActions(@RequestBody List<PrivilegeDTO> privilegeDTOS) {
        return adminService.addPrivilegesActions(privilegeDTOS);
    }
}
