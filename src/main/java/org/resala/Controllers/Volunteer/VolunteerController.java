package org.resala.Controllers.Volunteer;


import org.resala.Controllers.AuthorizeController;
import org.resala.Controllers.CommonController;
import org.resala.Models.Auth.Response;
import org.resala.Service.IssTokenService;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.StaticNames;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController implements CommonController<VolunteerDTO> {
    @Autowired
    VolunteerService volunteerService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteers + "') or hasRole('" + StaticNames.getAllVolunteersPublicInfo + "')" +
            "or hasRole('" + StaticNames.getVolunteersByMyBranchId + "') or hasRole('" + StaticNames.getVolunteersPublicInfoByMyBranch + "')")
    @Override
    public ResponseEntity<Object> getAll() {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllVolunteers, authorities))
            return ResponseEntity.ok(new Response(volunteerService.getAll(), HttpStatus.OK.value()));
        else if (AuthorizeController.contain(StaticNames.getAllVolunteersPublicInfo, authorities))
            return ResponseEntity.ok(new Response(volunteerService.getAllPublicInfo(), HttpStatus.OK.value()));

//        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        if (AuthorizeController.contain(StaticNames.getVolunteersByMyBranchId, authorities))
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersProjectionByBranch(IssTokenService.getBranchId()), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByBranch(IssTokenService.getBranchId()), HttpStatus.OK.value()));


    }

    /*@RequestMapping(value = "/getAllPublicInfo", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllPublicInfo() {
        return ResponseEntity.ok(new Response(volunteerService.getAllPublicInfo(), HttpStatus.OK.value()));
    }*/


    @RequestMapping(value = "/getAllByState/{stateId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteersByState + "') or hasRole('" + StaticNames.getAllVolunteersPublicInfoByState + "')" +
            "or hasRole('" + StaticNames.getAllVolunteersByStateAndMyBranch + "') or hasRole('" + StaticNames.getAllVolunteersPublicInfoByStateAndMyBranch + "')")
    public ResponseEntity<Object> getAllByState(@PathVariable int stateId) {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllVolunteersByState, authorities))
            return ResponseEntity.ok(new Response(volunteerService.getAllByState(stateId), HttpStatus.OK.value()));
        else if (AuthorizeController.contain(StaticNames.getAllVolunteersPublicInfoByState, authorities))
            return ResponseEntity.ok(new Response(volunteerService.getAllPublicInfoByState(stateId), HttpStatus.OK.value()));

//        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        if (AuthorizeController.contain(StaticNames.getAllVolunteersByStateAndMyBranch, authorities)) {
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersByStateAndBranch(stateId, IssTokenService.getBranchId()), HttpStatus.OK.value()));

        } else {
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByStateAndBranch(stateId, IssTokenService.getBranchId()), HttpStatus.OK.value()));
        }

    }

   /* @RequestMapping(value = "/getAllActivePublicInfo", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllActivePublicInfo() {
        return ResponseEntity.ok(new Response(volunteerService.getAllByStatePublicInfo(StaticNames.activeState), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getAllRequestedToArchive", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveVolunteers + "')")
    public ResponseEntity<Object> getAllRequestedToArchive() {
        return ResponseEntity.ok(new Response(volunteerService.getAllByState(StaticNames.requestedToArchiveState), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllRequestedToArchivePublicInfo", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllARequestedToArchivePublicInfo() {
        return ResponseEntity.ok(new Response(volunteerService.getAllByStatePublicInfo(StaticNames.requestedToArchiveState), HttpStatus.OK.value()));
    }


    @RequestMapping(value = "/getAllArchived", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedVolunteers + "')")
    public ResponseEntity<Object> getAllArchived() {
        return ResponseEntity.ok(new Response(volunteerService.getAllByState(StaticNames.archivedState), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllArchivedPublicInfo", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllArchivedPublicInfo() {
        return ResponseEntity.ok(new Response(volunteerService.getAllByStatePublicInfo(StaticNames.archivedState), HttpStatus.OK.value()));
    }

*/

    @RequestMapping(value = "/getAllByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteers + "') or hasRole('" + StaticNames.getAllVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllByBranchId(@PathVariable int branchId) {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllVolunteers, authorities))
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersProjectionByBranch(branchId), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByBranch(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllByPhoneNumber", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteers + "') or hasRole('" + StaticNames.getAllVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllByBranchId(@RequestBody String phoneNumber) {

        return ResponseEntity.ok(new Response(volunteerService.getVolunteerByPhoneNumber(phoneNumber), HttpStatus.OK.value()));
    }

    /*@RequestMapping(value = "/getAllByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getVolunteersByMyBranchId + "') or hasRole('" + StaticNames.getVolunteersPublicInfoByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getVolunteersByMyBranchId, authorities)) {
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersProjectionByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));
        } else {
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));

        }
    }*/

    /*@RequestMapping(value = "/getAllPublicInfoByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getPublicInfoByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByBranch(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllPublicInfoByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getVolunteersPublicInfoByMyBranchId + "')")
    public ResponseEntity<Object> getAllPublicInfoByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }*/


    @RequestMapping(value = "/getAllByStateAndBranch/{stateId}/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteersByState + "') or hasRole('" + StaticNames.getAllVolunteersPublicInfoByState + "')")
    public ResponseEntity<Object> getAllByStateAndBranchId(@PathVariable int stateId, @PathVariable int branchId) {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllVolunteersByState, authorities))
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersByStateAndBranch(stateId, branchId), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByStateAndBranch(stateId, branchId), HttpStatus.OK.value()));
    }

    /*@RequestMapping(value = "/getAllByStateAndBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteersByStateAndMyBranch + "') or hasRole('" + StaticNames.getAllVolunteersPublicInfoByStateAndMyBranch + "') ")
    public ResponseEntity<Object> getAllByStateAndMyBranchId(@RequestBody VolunteerStatusDTO volunteerStatusDTO) {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllVolunteersByStateAndMyBranch, authorities)) {
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersByStateAndBranch(volunteerStatusDTO.getId(), Integer.parseInt(branchId)), HttpStatus.OK.value()));

        } else {
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByStateAndBranch(volunteerStatusDTO.getId(), Integer.parseInt(branchId)), HttpStatus.OK.value()));
        }

    }*/

    /*@RequestMapping(value = "/getRequestedToArchivePublicInfoByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllRequestedToArchivePublicInfoByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByStateAndBranch(StaticNames.requestedToArchiveState, branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getRequestedToArchivePublicInfoByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveVolunteersPublicInfoByMyBranchId + "')")
    public ResponseEntity<Object> getAllARequestedToArchivePublicInfoByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByStateAndBranch(StaticNames.requestedToArchiveState, Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }


    @RequestMapping(value = "/getActiveByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveVolunteers + "')")
    @Override
    public ResponseEntity<Object> getAllActiveByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(volunteerService.getVolunteersByStateAndBranch(StaticNames.activeState, branchId), HttpStatus.OK.value()));
    }

   @RequestMapping(value = "/getActiveByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveVolunteersByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllActiveByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(volunteerService.getVolunteersByStateAndBranch(StaticNames.activeState, Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }

    @RequestMapping(value = "/getActivePublicInfoByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllActivePublicInfoByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByStateAndBranch(StaticNames.activeState, branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getActivePublicInfoByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveVolunteersPublicInfoByMyBranchId + "')")
    public ResponseEntity<Object> getAllActivePublicInfoByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByStateAndBranch(StaticNames.activeState, Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }


    @RequestMapping(value = "/getArchivedByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedVolunteers + "')")
    @Override
    public ResponseEntity<Object> getAllArchivedByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(volunteerService.getVolunteersByStateAndBranch(StaticNames.archivedState, branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getArchivedByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedVolunteersByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllArchivedByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(volunteerService.getVolunteersByStateAndBranch(StaticNames.archivedState, Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }

    @RequestMapping(value = "/getArchivedPublicInfoByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllArchivedPublicInfoByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByStateAndBranch(StaticNames.archivedState, branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getArchivedPublicInfoByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedVolunteersPublicInfoByMyBranchId + "')")
    public ResponseEntity<Object> getAllArchivedPublicInfoByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByStateAndBranch(StaticNames.archivedState, Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }*/


    @Override
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.createVolunteer + "')")
    public ResponseEntity<Object> add(@RequestBody List<VolunteerDTO> volunteerDTO) {
        return volunteerService.create(volunteerDTO);
    }


    @RequestMapping(value = "/requestToArchive", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.requestToArchiveVolunteer + "')")
    public ResponseEntity<Object> requestToArchive(@RequestBody VolunteerDTO obj) {
        return volunteerService.requestToArchive(obj);
    }

    @RequestMapping(value = "/declineToArchive", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.declineToArchiveVolunteer + "')")
    public ResponseEntity<Object> declineToArchive(@RequestBody VolunteerDTO obj) {
        return volunteerService.declineToArchive(obj);
    }

    @Override
    @RequestMapping(value = "/acceptToArchive", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.acceptToArchiveVolunteer + "')")
    public ResponseEntity<Object> archive(@RequestBody VolunteerDTO obj) {
        return volunteerService.archive(obj);
    }

    @Override
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('" + StaticNames.updateVolunteer + "')")
    public ResponseEntity<Object> update(@RequestBody VolunteerDTO newObj) {
        return volunteerService.update(newObj);
    }


}
