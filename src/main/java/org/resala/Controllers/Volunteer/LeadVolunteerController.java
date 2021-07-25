package org.resala.Controllers.Volunteer;

import org.resala.Controllers.AuthorizeController;
import org.resala.Models.Auth.Response;
import org.resala.Projections.LeadVolunteer.LeadVolunteerProjection;
import org.resala.Projections.LeadVolunteer.LeadVolunteerPublicInfoProjection;
import org.resala.Service.TokenService;
import org.resala.Service.Volunteer.LeadVolunteerService;
import org.resala.StaticNames;
import org.resala.dto.Privilege.PrivilegeDTO;
import org.resala.dto.Volunteer.LeadVolunteerDTO;
import org.resala.dto.Volunteer.UserDTO;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/leadVolunteer")
public class LeadVolunteerController {
    @Autowired
    LeadVolunteerService leadVolunteerService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllLeadVolunteers + "') or hasRole('" + StaticNames.getAllLeadVolunteersPublicInfo + "')" +
            "or hasRole('" + StaticNames.getLeadVolunteersByMyBranchId + "') or hasRole('" + StaticNames.getLeadVolunteersPublicInfoByMyBranch + "')")
    public ResponseEntity<Object> getAll() {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllLeadVolunteers, authorities)) {
            return ResponseEntity.ok(new Response(leadVolunteerService.getAll(LeadVolunteerProjection.class), HttpStatus.OK.value()));
        } else if (AuthorizeController.contain(StaticNames.getAllLeadVolunteersPublicInfo, authorities))
            return ResponseEntity.ok(new Response(leadVolunteerService.getAll(LeadVolunteerPublicInfoProjection.class), HttpStatus.OK.value()));

        int branchId = TokenService.getBranchId();
        if (AuthorizeController.contain(StaticNames.getLeadVolunteersByMyBranchId, authorities))
            return ResponseEntity.ok(new Response(leadVolunteerService.getLeadVolunteersProjectionByBranch(branchId), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(leadVolunteerService.getLeadVolunteersPublicInfoByBranch(branchId), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getAllByState/{stateId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllLeadVolunteersByState + "') or hasRole('" + StaticNames.getAllLeadVolunteersPublicInfoByState + "')" +
            "or hasRole('" + StaticNames.getAllLeadVolunteersByStateAndMyBranch + "') or hasRole('" + StaticNames.getAllLeadVolunteersPublicInfoByStateAndMyBranch + "')")
    public ResponseEntity<Object> getAllActive(@PathVariable int stateId) {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllLeadVolunteersByState, authorities))
            return ResponseEntity.ok(new Response(leadVolunteerService.getAllByState(stateId), HttpStatus.OK.value()));
        else if (AuthorizeController.contain(StaticNames.getAllLeadVolunteersPublicInfoByState, authorities))
            return ResponseEntity.ok(new Response(leadVolunteerService.getAllByStatePublicInfo(stateId), HttpStatus.OK.value()));

        int branchId = TokenService.getBranchId();
        if (AuthorizeController.contain(StaticNames.getAllLeadVolunteersByStateAndMyBranch, authorities))
            return ResponseEntity.ok(new Response(leadVolunteerService.getAllByStateAndBranch(stateId, branchId), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfoByStateAndBranch(stateId, branchId), HttpStatus.OK.value()));

    }

    @RequestMapping(value = "/getBranchCommitteeTeam", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getMyBranchCommitteeTeam + "')")
    public ResponseEntity<Object> getCommitteeTeam() {
        return ResponseEntity.ok(new Response(leadVolunteerService.getCommitteeTeam(

                TokenService.getBranchId(), TokenService.getMyCommitteeId()), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getAllByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllLeadVolunteers + "') or hasRole('" + StaticNames.getAllLeadVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllByBranchId(@PathVariable int branchId) {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllLeadVolunteers, authorities))
            return ResponseEntity.ok(new Response(leadVolunteerService.getLeadVolunteersProjectionByBranch(branchId), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(leadVolunteerService.getLeadVolunteersPublicInfoByBranch(branchId), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getAllByStateAndBranch/{stateId}/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllLeadVolunteersByState + "') or hasRole('" + StaticNames.getAllLeadVolunteersPublicInfoByState + "')")
    public ResponseEntity<Object> getAllByStateAndBranchId(@PathVariable int stateId, @PathVariable int branchId) {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllLeadVolunteersByState, authorities))
            return ResponseEntity.ok(new Response(leadVolunteerService.getAllByStateAndBranch(stateId, branchId), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfoByStateAndBranch(stateId, branchId), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.createLeadVolunteer + "')")
    public ResponseEntity<Object> add(@RequestBody List<LeadVolunteerDTO> leadVolunteerDTOs) {
        return leadVolunteerService.create(leadVolunteerDTOs);
    }


    @RequestMapping(value = "/createVolunteerUser", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.createUser + "')")
    public ResponseEntity<Object> createUser(@RequestBody List<VolunteerDTO> dtos) {
        return leadVolunteerService.createVolunteerUser(dtos);
    }


    @RequestMapping(value = "/addPrivileges", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.createPrivilege + "')")
    public ResponseEntity<Object> addPrivileges(@RequestBody List<PrivilegeDTO> privilegeDTOS) {
        return leadVolunteerService.addPrivileges(privilegeDTOS);
    }

    @RequestMapping(value = "/addPrivilegesActions", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.assignActionsToPrivilege + "')")
    public ResponseEntity<Object> addPrivilegesActions(@RequestBody List<PrivilegeDTO> privilegeDTOS) {
        return leadVolunteerService.addPrivilegesActions(privilegeDTOS);
    }

    @RequestMapping(value = "/addUserPrivileges", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.assignUserPrivileges + "')")
    public ResponseEntity<Object> addUserPrivileges(@RequestBody List<UserDTO> userDTOS) {
        return leadVolunteerService.addUserPrivileges(userDTOS);
    }
}
