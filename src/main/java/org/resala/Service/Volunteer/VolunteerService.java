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
import org.resala.Service.CommonCRUDService;
import org.resala.Service.CommonService;
import org.resala.Service.Privilege.PrivilegeService;
import org.resala.StaticNames;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VolunteerService implements CommonCRUDService<VolunteerDTO>, CommonService<Volunteer> {
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
        Branch branch = branchService.get(obj.getBranchId());
        Capital capital = capitalService.get(obj.getAddress().getCapitalId());
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
        return ResponseEntity.ok(new Response(StaticNames.addedSuccessfully, HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> delete(VolunteerDTO obj) {
        Volunteer volunteer = get(obj.getId());
        VolunteerStatus volunteerStatus = volunteerStatusService.getVolunteerStatusByName(StaticNames.deletedState);
        volunteer.setVolunteerStatus(volunteerStatus);
        volunteerRepo.save(volunteer);
        return ResponseEntity.ok(new Response(StaticNames.deletedSuccessfully, HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> update(VolunteerDTO newObj) {
        Volunteer volunteer = get(newObj.getId());
        Branch branch = branchService.get(newObj.getBranchId());
        Capital capital = capitalService.get(newObj.getAddress().getCapitalId());
        Volunteer newVolunteer = modelMapper().map(newObj, Volunteer.class);
        newVolunteer.setId(volunteer.getId());
        newVolunteer.setBranch(branch);
        newVolunteer.getAddress().setCapital(capital);
        newVolunteer.setRole(volunteer.getRole());
        newVolunteer.setPrivileges(volunteer.getPrivileges());
        newVolunteer.setVolunteerStatus(volunteer.getVolunteerStatus());
        volunteerRepo.save(newVolunteer);
        return ResponseEntity.ok(new Response(StaticNames.updatedSuccessfully, HttpStatus.OK.value()));
    }

    @Override
    public Volunteer get(int id) {
        Optional<Volunteer> optionalVolunteer = volunteerRepo.findById(id);
        if (!optionalVolunteer.isPresent())
            throw new MyEntityNotFoundException("Volunteer "+StaticNames.notFound);
        return optionalVolunteer.get();
    }

    @Override
    public List<Volunteer> getAll() {
        return volunteerRepo.findAll();
    }

    public List<Volunteer> getVolunteersByBranch(int branchId) {
        branchService.get(branchId);
        return volunteerRepo.findByBranch_id(branchId);
    }

}
