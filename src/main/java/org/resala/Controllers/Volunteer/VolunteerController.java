package org.resala.Controllers.Volunteer;


import org.resala.Controllers.CommonActiveBranchStateController;
import org.resala.Controllers.CommonBranchController;
import org.resala.Controllers.CommonController;
import org.resala.Models.Auth.Response;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.StaticNames;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController implements CommonController<VolunteerDTO>, CommonBranchController, CommonActiveBranchStateController {
    @Autowired
    VolunteerService volunteerService;

    @RequestMapping(value = "/getAllVolunteers", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteers + "')")
    @Override
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(new Response(volunteerService.getAll(), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllVolunteersPublicInfo", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllPublicInfo() {
        return ResponseEntity.ok(new Response(volunteerService.getAllPublicInfo(), HttpStatus.OK.value()));
    }




    @RequestMapping(value = "/getAllActiveVolunteers", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveVolunteers + "')")
    public ResponseEntity<Object> getAllActive() {
        return ResponseEntity.ok(new Response(volunteerService.getAllActive(), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllActiveVolunteersPublicInfo", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllActivePublicInfo() {
        return ResponseEntity.ok(new Response(volunteerService.getAllActivePublicInfo(), HttpStatus.OK.value()));
    }




    @RequestMapping(value = "/getAllRequestedToArchiveVolunteers", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveVolunteers + "')")
    public ResponseEntity<Object> getAllRequestedToArchive() {
        return ResponseEntity.ok(new Response(volunteerService.getAllRequestedToArchive(), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllRequestedToArchiveVolunteersPublicInfo", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllARequestedToArchivePublicInfo() {
        return ResponseEntity.ok(new Response(volunteerService.getAllRequestedToArchivePublicInfo(), HttpStatus.OK.value()));
    }



    @RequestMapping(value = "/getAllArchivedVolunteers", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedVolunteers + "')")
    public ResponseEntity<Object> getAllArchived() {
        return ResponseEntity.ok(new Response(volunteerService.getAllArchived(), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getAllArchivedVolunteersPublicInfo", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllArchivedPublicInfo() {
        return ResponseEntity.ok(new Response(volunteerService.getAllArchivedPublicInfo(), HttpStatus.OK.value()));
    }







    @RequestMapping(value = "/getVolunteersByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteers + "')")
    @Override
    public ResponseEntity<Object> getAllByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(volunteerService.getVolunteersByBranch(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getVolunteersByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getVolunteersByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(volunteerService.getVolunteersByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }
    @RequestMapping(value = "/getVolunteersPublicInfoByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getPublicInfoByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByBranch(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getVolunteersPublicInfoByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getVolunteersPublicInfoByMyBranchId + "')")
    public ResponseEntity<Object> getAllPublicInfoByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }







    @RequestMapping(value = "/getRequestedToArchiveVolunteersByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveVolunteers + "')")
    public ResponseEntity<Object> getAllRequestedToArchiveByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(volunteerService.getRequestedToArchiveVolunteersByBranch(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getRequestedToArchiveVolunteersByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveVolunteersByMyBranchId + "')")
    public ResponseEntity<Object> getAllRequestedToArchiveByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(volunteerService.getRequestedToArchiveVolunteersByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }
    @RequestMapping(value = "/getRequestedToArchiveVolunteersPublicInfoByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllRequestedToArchivePublicInfoByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(volunteerService.getRequestedToArchiveVolunteersPublicInfoByBranch(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getRequestedToArchiveVolunteersPublicInfoByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllRequestedToArchiveVolunteersPublicInfoByMyBranchId + "')")
    public ResponseEntity<Object> getAllARequestedToArchivePublicInfoByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(volunteerService.getRequestedToArchiveVolunteersPublicInfoByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }





    @RequestMapping(value = "/getActiveVolunteersByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveVolunteers + "')")
    @Override
    public ResponseEntity<Object> getAllActiveByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(volunteerService.getActiveVolunteersByBranch(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getActiveVolunteersByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveVolunteersByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllActiveByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(volunteerService.getActiveVolunteersByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }

    @RequestMapping(value = "/getActiveVolunteersPublicInfoByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllActivePublicInfoByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(volunteerService.getActiveVolunteersPublicInfoByBranch(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getActiveVolunteersPublicInfoByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllActiveVolunteersPublicInfoByMyBranchId + "')")
    public ResponseEntity<Object> getAllActivePublicInfoByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(volunteerService.getActiveVolunteersPublicInfoByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }






    @RequestMapping(value = "/getArchivedVolunteersByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedVolunteers + "')")
    @Override
    public ResponseEntity<Object> getAllArchivedByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(volunteerService.getArchivedVolunteersByBranch(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getArchivedVolunteersByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedVolunteersByMyBranchId + "')")
    @Override
    public ResponseEntity<Object> getAllArchivedByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(volunteerService.getArchivedVolunteersByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }

    @RequestMapping(value = "/getArchivedVolunteersPublicInfoByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllArchivedPublicInfoByBranchId(@PathVariable int branchId) {
        return ResponseEntity.ok(new Response(volunteerService.getArchivedVolunteersPublicInfoByBranch(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getArchivedVolunteersPublicInfoByBranch", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllArchivedVolunteersPublicInfoByMyBranchId + "')")
    public ResponseEntity<Object> getAllArchivedPublicInfoByMyBranchId() {
        String branchId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return ResponseEntity.ok(new Response(volunteerService.getArchivedVolunteersPublicInfoByBranch(Integer.parseInt(branchId)), HttpStatus.OK.value()));

    }








    @Override
    @RequestMapping(value = "/addVolunteer", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.createVolunteer + "')")
    public ResponseEntity<Object> add(@RequestBody VolunteerDTO volunteerDTO) {
        return volunteerService.create(volunteerDTO);
    }


    @RequestMapping(value = "/requestToArchiveVolunteer", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.requestToArchiveVolunteer + "')")
    public ResponseEntity<Object> requestToArchive(@RequestBody VolunteerDTO obj) {
        return volunteerService.requestToArchive(obj);
    }

    @RequestMapping(value = "/declineToArchiveVolunteer", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.declineToArchiveVolunteer + "')")
    public ResponseEntity<Object> declineToArchive(@RequestBody VolunteerDTO obj) {
        return volunteerService.declineToArchive(obj);
    }

    @Override
    @RequestMapping(value = "/acceptToArchiveVolunteer", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('" + StaticNames.acceptToArchiveVolunteer + "')")
    public ResponseEntity<Object> archive(@RequestBody VolunteerDTO obj) {
        return volunteerService.archive(obj);
    }

    @Override
    @RequestMapping(value = "/updateVolunteer", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('" + StaticNames.updateVolunteer + "')")
    public ResponseEntity<Object> update(@RequestBody VolunteerDTO newObj) {
        return volunteerService.update(newObj);
    }


}
