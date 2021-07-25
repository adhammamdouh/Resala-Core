package org.resala.Controllers.Volunteer;


import org.resala.Controllers.AuthorizeController;
import org.resala.Controllers.CommonController;
import org.resala.Models.Auth.Response;
import org.resala.Service.TokenService;
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

        if (AuthorizeController.contain(StaticNames.getVolunteersByMyBranchId, authorities))
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersProjectionByBranch(TokenService.getBranchId()), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByBranch(TokenService.getBranchId()), HttpStatus.OK.value()));


    }


    @RequestMapping(value = "/getAllByState/{stateId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteersByState + "') or hasRole('" + StaticNames.getAllVolunteersPublicInfoByState + "')" +
            "or hasRole('" + StaticNames.getAllVolunteersByStateAndMyBranch + "') or hasRole('" + StaticNames.getAllVolunteersPublicInfoByStateAndMyBranch + "')")
    public ResponseEntity<Object> getAllByState(@PathVariable int stateId) {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllVolunteersByState, authorities))
            return ResponseEntity.ok(new Response(volunteerService.getAllByState(stateId), HttpStatus.OK.value()));
        else if (AuthorizeController.contain(StaticNames.getAllVolunteersPublicInfoByState, authorities))
            return ResponseEntity.ok(new Response(volunteerService.getAllPublicInfoByState(stateId), HttpStatus.OK.value()));

        if (AuthorizeController.contain(StaticNames.getAllVolunteersByStateAndMyBranch, authorities)) {
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersByStateAndBranch(stateId, TokenService.getBranchId()), HttpStatus.OK.value()));

        } else {
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByStateAndBranch(stateId, TokenService.getBranchId()), HttpStatus.OK.value()));
        }

    }


    @RequestMapping(value = "/getAllByBranch/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteers + "') or hasRole('" + StaticNames.getAllVolunteersPublicInfo + "')")
    public ResponseEntity<Object> getAllByBranchId(@PathVariable int branchId) {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllVolunteers, authorities))
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersProjectionByBranch(branchId), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByBranch(branchId), HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/getByPhoneNumber", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.getVolunteerByPhoneNumber + "') ")
    public ResponseEntity<Object> getByPhoneNumber(@RequestBody VolunteerDTO dto) {

        return ResponseEntity.ok(new Response(volunteerService.getVolunteerByPhoneNumber(dto), HttpStatus.OK.value()));
    }





    @RequestMapping(value = "/getAllByStateAndBranch/{stateId}/{branchId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + StaticNames.getAllVolunteersByState + "') or hasRole('" + StaticNames.getAllVolunteersPublicInfoByState + "')")
    public ResponseEntity<Object> getAllByStateAndBranchId(@PathVariable int stateId, @PathVariable int branchId) {
        Collection<? extends GrantedAuthority> authorities = AuthorizeController.getAuthorities();
        if (AuthorizeController.contain(StaticNames.getAllVolunteersByState, authorities))
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersByStateAndBranch(stateId, branchId), HttpStatus.OK.value()));
        else
            return ResponseEntity.ok(new Response(volunteerService.getVolunteersPublicInfoByStateAndBranch(stateId, branchId), HttpStatus.OK.value()));
    }




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

    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + StaticNames.activateVolunteer + "')")
    public ResponseEntity<Object> activate(@RequestBody VolunteerDTO obj) {
        return volunteerService.activate(obj);
    }

    @Override
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('" + StaticNames.updateVolunteer + "')")
    public ResponseEntity<Object> update(@RequestBody VolunteerDTO newObj) {
        return volunteerService.update(newObj);
    }


}
