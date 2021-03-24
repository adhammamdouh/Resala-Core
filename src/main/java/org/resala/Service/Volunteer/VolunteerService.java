package org.resala.Service.Volunteer;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.ConstraintViolationException;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Address.Capital;
import org.resala.Models.Auth.Response;
import org.resala.Models.Branch;
import org.resala.Models.Privilege.Privilege;
import org.resala.Models.Volunteer.Role;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Models.Volunteer.VolunteerStatus;
import org.resala.Projections.VolunteerPublicInfoProjection;
import org.resala.Repository.Volunteer.VolunteerRepo;
import org.resala.Service.Address.AddressService;
import org.resala.Service.Address.CapitalService;
import org.resala.Service.BranchService;
import org.resala.Service.CheckConstraintService;
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
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
    @Autowired
    AddressService addressService;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    @Override
    public ResponseEntity<Object> create(VolunteerDTO dto) {
        dto.checkNull();
        Branch branch = branchService.get(dto.getBranch().getId());
        Capital capital = capitalService.get(dto.getAddress().getCapital().getId());
        Role role = roleService.getRoleByName(StaticNames.normalVolunteer);
        VolunteerStatus volunteerStatus = volunteerStatusService.getVolunteerStatus(StaticNames.activeState);
        Privilege privilege = privilegeService.getPrivilegeByName(StaticNames.normalVolunteer);
        Volunteer volunteer = modelMapper().map(dto, Volunteer.class);
        volunteer.setBranch(branch);
        volunteer.getAddress().setCapital(capital);
        volunteer.setPrivileges(Stream.of(privilege).collect(toList()));
        volunteer.setRole(role);
        volunteer.setVolunteerStatus(volunteerStatus);
        checkConstraintViolations(volunteer);
        addressService.checkConstraintViolations(volunteer.getAddress());
        volunteerRepo.save(volunteer);
        return ResponseEntity.ok(new Response("Created Successfully", HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> delete(VolunteerDTO dto) {
        Volunteer volunteer = get(dto.getId());
        VolunteerStatus volunteerStatus = volunteerStatusService.getVolunteerStatus(StaticNames.deletedState);
        volunteer.setVolunteerStatus(volunteerStatus);
        volunteerRepo.save(volunteer);
        return ResponseEntity.ok(new Response("Deleted Successfully", HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> update(VolunteerDTO newDto) {
        newDto.checkNull();
        Volunteer volunteer = get(newDto.getId());
        /*if(newObj.getAddress().getId()!=volunteer.getAddress().getId()){
            throw new ConstraintViolationException("You can't change your address id");
        }*/

        Branch branch = branchService.get(newDto.getBranch().getId());
        Capital capital = capitalService.get(newDto.getAddress().getCapital().getId());
        Volunteer newVolunteer = modelMapper().map(newDto, Volunteer.class);
        newVolunteer.setId(volunteer.getId());
        newVolunteer.setBranch(branch);
        newVolunteer.getAddress().setId(volunteer.getAddress().getId());
        newVolunteer.getAddress().setCapital(capital);
        newVolunteer.setRole(volunteer.getRole());
        newVolunteer.setPrivileges(volunteer.getPrivileges());
        newVolunteer.setVolunteerStatus(volunteer.getVolunteerStatus());
        checkConstraintViolations(newVolunteer);
        addressService.checkConstraintViolations(newVolunteer.getAddress());
        volunteerRepo.save(newVolunteer);
        return ResponseEntity.ok(new Response(StaticNames.updatedSuccessfully, HttpStatus.OK.value()));
    }

    @Override
    public Volunteer get(int id) {
        Optional<Volunteer> optionalVolunteer = volunteerRepo.findById(id);
        if (!optionalVolunteer.isPresent())
            throw new MyEntityNotFoundException("Volunteer " + StaticNames.notFound);
        return optionalVolunteer.get();
    }

    public List<Volunteer> getVolunteerByIds(List<Integer> ids) {
        List<Volunteer> volunteers = volunteerRepo.findAllById(ids);
        if (volunteers.size() != ids.size()) {
            ids.removeAll(volunteers.stream().map(Volunteer::getId).collect(toList()));
            throw new MyEntityNotFoundException("Volunteers with id's " + ids + " does not exist");
        }
        return volunteers;
    }

    @Override
    public List<Volunteer> getAll() {
        return volunteerRepo.findAll();
    }

    public List<VolunteerPublicInfoProjection> getAllPublicInfo(){
        return volunteerRepo.findAllBy(VolunteerPublicInfoProjection.class);
    }

    public List<Volunteer> getVolunteersByBranch(int branchId) {
        branchService.get(branchId);
        return volunteerRepo.findByBranch_id(branchId);
    }

    public void checkConstraintViolations(Volunteer volunteer){
        CheckConstraintService.checkConstraintViolations(volunteer,Volunteer.class);
    }
}
