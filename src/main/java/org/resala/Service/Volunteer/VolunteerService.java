package org.resala.Service.Volunteer;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Address.Capital;
import org.resala.Models.Auth.Response;
import org.resala.Models.Branch;
import org.resala.Models.Privilege.Privilege;
import org.resala.Models.Volunteer.Role;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Models.Volunteer.VolunteerStatus;
import org.resala.Repository.Volunteer.VolunteerRepo;
import org.resala.Service.Address.CapitalService;
import org.resala.Service.BranchService;
import org.resala.Service.CommonService;
import org.resala.Service.Privilege.PrivilegeService;
import org.resala.StaticNames;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VolunteerService implements CommonService<VolunteerDTO> {
    @Autowired
    VolunteerRepo volunteerRepo;
    @Autowired
    BranchService branchService;
    @Autowired
    CapitalService capitalService;
    @Autowired
    PrivilegeService privilegeService;
    @Autowired
    RoleService roleService;
    @Autowired
    VolunteerStatusService volunteerStatusService;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    @Override
    public ResponseEntity<Object> create(VolunteerDTO obj) {
        Branch branch = branchService.getBranchById(obj.getBranchId());
        Capital capital = capitalService.getCapitalId(obj.getAddress().getCapitalId());
        Role role = roleService.getRoleByName(StaticNames.normalVolunteer);
        VolunteerStatus volunteerStatus = volunteerStatusService.getVolunteerStatusByName(StaticNames.activeState);
        Privilege privilege = privilegeService.getPrivilegeByName(StaticNames.normalVolunteer);
        Volunteer volunteer = modelMapper().map(obj, Volunteer.class);
        volunteer.setBranch(branch);
        volunteer.getAddress().setCapital(capital);
        volunteer.setPrivileges(Stream.of(privilege).collect(Collectors.toList()));
        volunteer.setRole(role);
        volunteer.setVolunteerStatus(volunteerStatus);
        volunteerRepo.save(volunteer);
        return ResponseEntity.ok(new Response("Created Successfully", HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> delete(VolunteerDTO obj) {
        Volunteer volunteer = getVolunteerById(obj.getId());
        VolunteerStatus volunteerStatus = volunteerStatusService.getVolunteerStatusByName(StaticNames.deletedState);
        volunteer.setVolunteerStatus(volunteerStatus);
        volunteerRepo.save(volunteer);
        return ResponseEntity.ok(new Response("Deleted Successfully", HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> update(VolunteerDTO newObj) {
        Volunteer volunteer = getVolunteerById(newObj.getId());
        Branch branch = branchService.getBranchById(newObj.getBranchId());
        Capital capital = capitalService.getCapitalId(newObj.getAddress().getCapitalId());
        Volunteer newVolunteer = modelMapper().map(newObj, Volunteer.class);
        newVolunteer.setId(volunteer.getId());
        newVolunteer.setBranch(branch);
        newVolunteer.getAddress().setCapital(capital);
        newVolunteer.setRole(volunteer.getRole());
        newVolunteer.setPrivileges(volunteer.getPrivileges());
        newVolunteer.setVolunteerStatus(volunteer.getVolunteerStatus());
        volunteerRepo.save(newVolunteer);
        return ResponseEntity.ok(new Response("Updated Successfully", HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> get(int id) {
        return ResponseEntity.ok(volunteerRepo.findById(id));
    }

    public ResponseEntity<Object> getAllVolunteers() {
        return ResponseEntity.ok().body(volunteerRepo.findAll());

    }

    public ResponseEntity<Object> getVolunteersByBranch(int branchId) {
        return ResponseEntity.ok().body(volunteerRepo.findByBranch_id(branchId));
    }

    public Volunteer getVolunteerById(int id) {
        Optional<Volunteer> optionalVolunteer = volunteerRepo.findById(id);
        if (!optionalVolunteer.isPresent())
            throw new MyEntityNotFoundException("Volunteer Not Found");
        return optionalVolunteer.get();
    }
}
