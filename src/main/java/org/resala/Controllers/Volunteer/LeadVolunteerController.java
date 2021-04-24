package org.resala.Controllers.Volunteer;

import org.resala.Controllers.CommonActiveBranchStateController;
import org.resala.Controllers.CommonBranchController;
import org.resala.Models.Auth.Response;
import org.resala.Projections.LeadVolunteerProjection;
import org.resala.Service.Volunteer.LeadVolunteerService;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leadVolunteer")
public class LeadVolunteerController implements CommonBranchController, CommonActiveBranchStateController {
    @Autowired
    LeadVolunteerService leadVolunteerService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllLeadVolunteers + "')")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAll(LeadVolunteerProjection.class), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllPublicInfo", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllLeadVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllPublicInfo() {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfo(), HttpStatus.OK.value()));
    }




    @RequestMapping(value = "/getAllActive", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveLeadVolunteers + "')")
    public ResponseEntity<Object> getAllActive() {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByState(StaticNames.activeState), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllActivePublicInfo", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveLeadVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllActivePublicInfo() {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByStatePublicInfo(StaticNames.activeState), HttpStatus.OK.value()));
    }




    @RequestMapping(value = "/getAllRequestedToArchive", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveLeadVolunteers + "')")
    public ResponseEntity<Object> getAllRequestedToArchive() {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByState(StaticNames.requestedToArchiveState), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllRequestedToArchivePublicInfo", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveLeadVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllARequestedToArchivePublicInfo() {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByStatePublicInfo(StaticNames.requestedToArchiveState), HttpStatus.OK.value()));
    }



    @RequestMapping(value = "/getAllArchived", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedLeadVolunteers + "')")
    public ResponseEntity<Object> getAllArchived() {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByState(StaticNames.archivedState), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllArchivedPublicInfo", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedLeadVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllArchivedPublicInfo() {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByStatePublicInfo(StaticNames.archivedState), HttpStatus.OK.value()));
    }







    @RequestMapping(value = "/getAllByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllLeadVolunteers + "')")
    @Override
    public ResponseEntity<Object> getAllByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(leadVolunteerService.getLeadVolunteersProjectionByBranch(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getLeadVolunteersByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(leadVolunteerService.getLeadVolunteersProjectionByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }
    @RequestMapping(value = "/getAllPublicInfoByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllLeadVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getPublicInfoByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(leadVolunteerService.getLeadVolunteersPublicInfoByBranch(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllPublicInfoByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getLeadVolunteersPublicInfoByMyBranchId + "')")
    public ResponseEntity<Object> getAllPublicInfoByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(leadVolunteerService.getLeadVolunteersPublicInfoByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }







    @RequestMapping(value = "/getRequestedToArchiveByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveLeadVolunteers + "')")
    public ResponseEntity<Object> getAllRequestedToArchiveByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByBranchAndState(StaticNames.requestedToArchiveState,branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getRequestedToArchiveByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveLeadVolunteersByMyBranchId + "')")
    public ResponseEntity<Object> getAllRequestedToArchiveByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByBranchAndState(StaticNames.requestedToArchiveState,Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }
    @RequestMapping(value = "/getRequestedToArchivePublicInfoByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveLeadVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllRequestedToArchivePublicInfoByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfoByBranchAndState(StaticNames.requestedToArchiveState,branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getRequestedToArchivePublicInfoByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveLeadVolunteersPublicInfoByMyBranchId + "')")
    public ResponseEntity<Object> getAllARequestedToArchivePublicInfoByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfoByBranchAndState(StaticNames.requestedToArchiveState,Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }





    @RequestMapping(value = "/getActiveByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveLeadVolunteers + "')")
    @Override
    public ResponseEntity<Object> getAllActiveByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByBranchAndState(StaticNames.activeState,branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getActiveByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveLeadVolunteersByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllActiveByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByBranchAndState(StaticNames.activeState,Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }

    @RequestMapping(value = "/getActivePublicInfoByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveLeadVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllActivePublicInfoByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfoByBranchAndState(StaticNames.activeState,branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getActivePublicInfoByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveLeadVolunteersPublicInfoByMyBranchId + "')")
    public ResponseEntity<Object> getAllActivePublicInfoByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfoByBranchAndState(StaticNames.activeState,Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }






    @RequestMapping(value = "/getArchivedByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedLeadVolunteers + "')")
    @Override
    public ResponseEntity<Object> getAllArchivedByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByBranchAndState(StaticNames.archivedState,branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getArchivedByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedLeadVolunteersByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllArchivedByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByBranchAndState(StaticNames.archivedState,Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }

    @RequestMapping(value = "/getArchivedPublicInfoByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedLeadVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllArchivedPublicInfoByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfoByBranchAndState(StaticNames.archivedState,branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getArchivedPublicInfoByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedLeadVolunteersPublicInfoByMyBranchId + "')")
    public ResponseEntity<Object> getAllArchivedPublicInfoByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfoByBranchAndState(StaticNames.archivedState,Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }
}
