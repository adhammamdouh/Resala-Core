package org.resala.Controllers.Volunteer;

import org.resala.Controllers.AuthorizeController;
import org.resala.Models.Auth.Response;
import org.resala.Projections.LeadVolunteer.LeadVolunteerProjection;
import org.resala.Projections.LeadVolunteer.LeadVolunteerPublicInfoProjection;
import org.resala.Service.Volunteer.LeadVolunteerService;
import org.resala.StaticNames;
import org.resala.dto.Volunteer.LeadVolunteerDTO;
import org.resala.dto.Volunteer.VolunteerStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
        if (AuthorizeController.contain(StaticNames.getAllLeadVolunteers, authorities)){
            return ResponseEntity.ok(new Response(leadVolunteerService.getAll(LeadVolunteerProjection.class), HttpStatus.OK.value()));
        }
        else if (AuthorizeController.contain(StaticNames.getAllLeadVolunteersPublicInfo, authorities))
            return ResponseEntity.ok(new Response(leadVolunteerService.getAll(LeadVolunteerPublicInfoProjection.class), HttpStatus.OK.value()));

        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        if (AuthorizeController.contain(StaticNames.getLeadVolunteersByMyBranchId, authorities))
            return ResponseEntity.ok(new Response(leadVolunteerService.getLeadVolunteersProjectionByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(leadVolunteerService.getLeadVolunteersPublicInfoByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));
    }

    /*@RequestMapping(value = "/getAllPublicInfo", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllLeadVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllPublicInfo() {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAll(LeadVolunteerPublicInfoProjection.class), HttpStatus.OK.value()));
    }*/


    @RequestMapping(value = "/getAllByState", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllLeadVolunteersByState + "') or hasRole('" + StaticNames.getAllLeadVolunteersPublicInfoByState + "')" +
            "or hasRole('" + StaticNames.getAllLeadVolunteersByStateAndMyBranch + "') or hasRole('" + StaticNames.getAllLeadVolunteersPublicInfoByStateAndMyBranch + "')")
    public ResponseEntity<Object> getAllActive(@RequestBody VolunteerStatusDTO volunteerStatusDTO) {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllLeadVolunteersByState, authorities))
            return ResponseEntity.ok(new Response(leadVolunteerService.getAllByState(volunteerStatusDTO.getId()), HttpStatus.OK.value()));
        else if (AuthorizeController.contain(StaticNames.getAllLeadVolunteersPublicInfoByState, authorities))
            return ResponseEntity.ok(new Response(leadVolunteerService.getAllByStatePublicInfo(volunteerStatusDTO.getId()), HttpStatus.OK.value()));

        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        if (AuthorizeController.contain(StaticNames.getAllLeadVolunteersByStateAndMyBranch, authorities))
            return ResponseEntity.ok(new Response(leadVolunteerService.getAllByStateAndBranch(volunteerStatusDTO.getId(), Integer.parseInt(branchId)), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfoByStateAndBranch(volunteerStatusDTO.getId(), Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }

    @RequestMapping(value = "/getCommitteeTeam",method = RequestMethod.GET)
    @PreAuthorize("hasRole('"+StaticNames.getCommitteeTeam+"')")
    public ResponseEntity<Object> getCommitteeTeam(@RequestBody LeadVolunteerDTO leadVolunteerDTO){
        return ResponseEntity.ok(new Response(leadVolunteerService.getCommitteeTeam(
                leadVolunteerDTO.getMyVolunteerInfo().getBranch(),leadVolunteerDTO.getCommittee()),HttpStatus.OK.value()));
    }

   /* @RequestMapping(value = "/getAllActivePublicInfo", method = RequestMethod.GET)
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
    }*/


    @RequestMapping(value = "/getAllByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllLeadVolunteers + "') or hasRole('" + StaticNames.getAllLeadVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllByBranchId(@PathVariable int branchId) {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllLeadVolunteers, authorities))
            return ResponseEntity.ok(new Response(leadVolunteerService.getLeadVolunteersProjectionByBranch(branchId), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(leadVolunteerService.getLeadVolunteersPublicInfoByBranch(branchId), HttpStatus.OK.value()));
    }

    /*@RequestMapping(value = "/getAllByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getLeadVolunteersByMyBranchId + "') or hasRole('" + StaticNames.getLeadVolunteersPublicInfoByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getLeadVolunteersByMyBranchId, authorities))
            return ResponseEntity.ok(new Response(leadVolunteerService.getLeadVolunteersProjectionByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(leadVolunteerService.getLeadVolunteersPublicInfoByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }*/

    /*@RequestMapping(value = "/getAllPublicInfoByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllLeadVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getPublicInfoByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(leadVolunteerService.getLeadVolunteersPublicInfoByBranch(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllPublicInfoByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getLeadVolunteersPublicInfoByMyBranchId + "')")
    public ResponseEntity<Object> getAllPublicInfoByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(leadVolunteerService.getLeadVolunteersPublicInfoByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }*/


    @RequestMapping(value = "/getAllByStateAndBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllLeadVolunteersByState + "') or hasRole('" + StaticNames.getAllLeadVolunteersPublicInfoByState + "')")
    public ResponseEntity<Object> getAllByStateAndBranchId(@RequestBody VolunteerStatusDTO volunteerStatusDTO, @PathVariable int branchId) {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllLeadVolunteersByState, authorities))
            return ResponseEntity.ok(new Response(leadVolunteerService.getAllByStateAndBranch(volunteerStatusDTO.getId(), branchId), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfoByStateAndBranch(volunteerStatusDTO.getId(), branchId), HttpStatus.OK.value()));
    }

    /*@RequestMapping(value = "/getAllByStateAndBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveLeadVolunteersByMyBranchId + "')")
    public ResponseEntity<Object> getAllRequestedToArchiveByMyBranchId(@RequestBody VolunteerStatusDTO volunteerStatusDTO) {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByStateAndBranch(StaticNames.requestedToArchiveState, Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }

    @RequestMapping(value = "/getRequestedToArchivePublicInfoByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveLeadVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllRequestedToArchivePublicInfoByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfoByStateAndBranch(StaticNames.requestedToArchiveState, branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getRequestedToArchivePublicInfoByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveLeadVolunteersPublicInfoByMyBranchId + "')")
    public ResponseEntity<Object> getAllARequestedToArchivePublicInfoByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfoByStateAndBranch(StaticNames.requestedToArchiveState, Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }


    @RequestMapping(value = "/getActiveByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveLeadVolunteers + "')")
    @Override
    public ResponseEntity<Object> getAllActiveByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByStateAndBranch(StaticNames.activeState, branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getActiveByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveLeadVolunteersByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllActiveByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByStateAndBranch(StaticNames.activeState, Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }

    @RequestMapping(value = "/getActivePublicInfoByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveLeadVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllActivePublicInfoByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfoByStateAndBranch(StaticNames.activeState, branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getActivePublicInfoByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveLeadVolunteersPublicInfoByMyBranchId + "')")
    public ResponseEntity<Object> getAllActivePublicInfoByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfoByStateAndBranch(StaticNames.activeState, Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }


    @RequestMapping(value = "/getArchivedByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedLeadVolunteers + "')")
    @Override
    public ResponseEntity<Object> getAllArchivedByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByStateAndBranch(StaticNames.archivedState, branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getArchivedByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedLeadVolunteersByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllArchivedByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllByStateAndBranch(StaticNames.archivedState, Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }

    @RequestMapping(value = "/getArchivedPublicInfoByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedLeadVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllArchivedPublicInfoByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfoByStateAndBranch(StaticNames.archivedState, branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getArchivedPublicInfoByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedLeadVolunteersPublicInfoByMyBranchId + "')")
    public ResponseEntity<Object> getAllArchivedPublicInfoByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(leadVolunteerService.getAllPublicInfoByStateAndBranch(StaticNames.archivedState, Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }*/

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.createLeadVolunteer + "')")
    public ResponseEntity<Object> add(@RequestBody LeadVolunteerDTO leadVolunteerDTO) {
        return leadVolunteerService.create(leadVolunteerDTO);
    }
}
