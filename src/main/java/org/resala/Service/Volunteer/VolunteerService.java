package org.resala.Service.Volunteer;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.ActiveStateException;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Address.Capital;
import org.resala.Models.Auth.Response;
import org.resala.Models.Branch;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.KPI.VolunteerKPI;
import org.resala.Models.Privilege.Privilege;
import org.resala.Models.Volunteer.Role;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Models.Volunteer.VolunteerStatus;
import org.resala.Projections.VolunteerProjection;
import org.resala.Projections.VolunteerPublicInfoProjection;
import org.resala.Repository.Volunteer.VolunteerRepo;
import org.resala.Service.Address.AddressService;
import org.resala.Service.Address.CapitalService;
import org.resala.Service.BranchService;
import org.resala.Service.Call.NetworkTypeService;
import org.resala.Service.CheckConstraintService;
import org.resala.Service.CommonCRUDService;
import org.resala.Service.Privilege.PrivilegeService;
import org.resala.StaticNames;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.resala.dto.Volunteer.VolunteerStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class VolunteerService implements CommonCRUDService<VolunteerDTO> {
    @Autowired
    private VolunteerRepo volunteerRepo;
    @Autowired
    private BranchService branchService;
    @Autowired
    private CapitalService capitalService;
    @Autowired
    private PrivilegeService privilegeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private VolunteerStatusService volunteerStatusService;
    @Autowired
    private AddressService addressService;
    @Autowired
    NetworkTypeService networkTypeService;

    //@Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    @Override
    public ResponseEntity<Object> create(VolunteerDTO dto) {
        dto.checkNull();
        Branch branch = branchService.getById(dto.getBranch().getId());
        Capital capital = capitalService.getById(dto.getAddress().getCapital().getId());
        Role role = roleService.getRoleByName(StaticNames.normalVolunteer);
        VolunteerStatus volunteerStatus = volunteerStatusService.getByName(StaticNames.activeState);
        Privilege privilege = privilegeService.getPrivilegeByName(StaticNames.normalVolunteer);
        String phoneNumber = dto.getPhoneNumber();
        Volunteer volunteer = modelMapper().map(dto, Volunteer.class);
        volunteer.setId(0);
        volunteer.setBranch(branch);
        volunteer.getAddress().setCapital(capital);
        volunteer.setPrivileges(Stream.of(privilege).collect(toList()));
        volunteer.setRole(role);
        volunteer.setVolunteerStatus(volunteerStatus);

        checkConstraintViolations(volunteer);
        addressService.checkConstraintViolations(volunteer.getAddress());
        volunteer.setNetworkType(networkTypeService.getNetworkTypeBasedOnVolunteerNumber(phoneNumber));
        volunteerRepo.save(volunteer);
        return ResponseEntity.ok(new Response(StaticNames.addedSuccessfully, HttpStatus.OK.value()));
    }


    public ResponseEntity<Object> requestToArchive(VolunteerDTO dto) {
        Volunteer volunteer = getById(dto.getId());
        if (!volunteer.getVolunteerStatus().getName().equals(StaticNames.activeState))
            throw new ActiveStateException("This Volunteer State is " + volunteer.getVolunteerStatus().getName());
        VolunteerStatus volunteerStatus = volunteerStatusService.getByName(StaticNames.requestedToArchiveState);
        volunteer.setVolunteerStatus(volunteerStatus);
        volunteerRepo.save(volunteer);
        return ResponseEntity.ok(new Response("Requested To Archive Successfully", HttpStatus.OK.value()));
    }

    public ResponseEntity<Object> declineToArchive(VolunteerDTO dto) {
        Volunteer volunteer = getById(dto.getId());
        if (!volunteer.getVolunteerStatus().getName().equals(StaticNames.requestedToArchiveState))
            throw new ActiveStateException("This Volunteer State is " + volunteer.getVolunteerStatus().getName());
        VolunteerStatus volunteerStatus = volunteerStatusService.getByName(StaticNames.activeState);
        volunteer.setVolunteerStatus(volunteerStatus);
        volunteerRepo.save(volunteer);
        return ResponseEntity.ok(new Response("Declined to Archive Successfully", HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> archive(VolunteerDTO dto) {
        Volunteer volunteer = getById(dto.getId());
        if (!volunteer.getVolunteerStatus().getName().equals(StaticNames.requestedToArchiveState))
            throw new ActiveStateException("This Volunteer State is " + volunteer.getVolunteerStatus().getName());
        VolunteerStatus volunteerStatus = volunteerStatusService.getByName(StaticNames.archivedState);
        volunteer.setVolunteerStatus(volunteerStatus);
        volunteerRepo.save(volunteer);
        return ResponseEntity.ok(new Response("Archived Successfully", HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> update(VolunteerDTO newDto) {
        newDto.checkNull();
        Volunteer volunteer = getById(newDto.getId());
        /*if(newObj.getAddress().getId()!=volunteer.getAddress().getId()){
            throw new ConstraintViolationException("You can't change your address id");
        }*/

        Branch branch = branchService.getById(newDto.getBranch().getId());
        Capital capital = capitalService.getById(newDto.getAddress().getCapital().getId());
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

    public Volunteer getById(int id) {
        Optional<Volunteer> optionalVolunteer = volunteerRepo.findById(id);
        if (!optionalVolunteer.isPresent())
            throw new MyEntityNotFoundException("Volunteer " + StaticNames.notFound);
        return optionalVolunteer.get();
    }

    public Volunteer getByUserName(String userName) {
        Optional<Volunteer> optionalVolunteer = volunteerRepo.findByUser_UserName(userName);
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

    public List<VolunteerProjection> getAll() {
        return volunteerRepo.findAllBy(VolunteerProjection.class);
    }

    public List<VolunteerPublicInfoProjection> getAllPublicInfo() {
        return volunteerRepo.findAllBy(VolunteerPublicInfoProjection.class);
    }


    public List<VolunteerProjection> getAllByState(int stateId) {
        VolunteerStatus volunteerStatus = volunteerStatusService.getById(stateId);
        return volunteerRepo.findAllByVolunteerStatus(volunteerStatus, VolunteerProjection.class);
    }

    public List<VolunteerPublicInfoProjection> getAllPublicInfoByState(int stateId) {
        VolunteerStatus volunteerStatus = volunteerStatusService.getById(stateId);
        return volunteerRepo.findAllByVolunteerStatus(volunteerStatus, VolunteerPublicInfoProjection.class);
    }


    /*public List<Volunteer> getAllArchived() {
        return volunteerRepo.findAllByVolunteerStatus_name(StaticNames.archivedState, Volunteer.class);
    }

    public List<VolunteerPublicInfoProjection> getAllArchivedPublicInfo() {
        return volunteerRepo.findAllByVolunteerStatus_name(StaticNames.archivedState, VolunteerPublicInfoProjection.class);
    }


    public List<Volunteer> getVolunteersByBranch(int branchId) {
        branchService.getById(branchId);
        return volunteerRepo.findByBranch_id(branchId, Volunteer.class);
    }*/

    public List<Volunteer> getVolunteersByBranchAndNetworkType(Branch branch, NetworkType networkType) {
        List<Volunteer> volunteers = new ArrayList<>();
//            System.out.println("branch is " + branch.getId());
//            System.out.println("network type is " + networkType.getName());
        volunteers.addAll(volunteerRepo.findByBranchAndNetworkTypeAndVolunteerStatus_Name
                (branch, networkType, StaticNames.activeState));


        return volunteers;
    }


    public List<VolunteerProjection> getVolunteersProjectionByBranch(int branchId) {
        branchService.getById(branchId);
        return volunteerRepo.findByBranch_id(branchId, VolunteerProjection.class);
    }

    public List<VolunteerPublicInfoProjection> getVolunteersPublicInfoByBranch(int branchId) {
        branchService.getById(branchId);
        return volunteerRepo.findByBranch_id(branchId, VolunteerPublicInfoProjection.class);
    }

    public List<VolunteerProjection> getVolunteersByStateAndBranch(int stateId, int branchId) {
        Branch branch = branchService.getById(branchId);
        VolunteerStatus volunteerStatus = volunteerStatusService.getById(stateId);
        return volunteerRepo.findAllByVolunteerStatusAndBranch(volunteerStatus, branch, VolunteerProjection.class);
    }

    public List<VolunteerPublicInfoProjection> getVolunteersPublicInfoByStateAndBranch(int stateId, int branchId) {
        Branch branch = branchService.getById(branchId);
        VolunteerStatus volunteerStatus=volunteerStatusService.getById(stateId);
        return volunteerRepo.findAllByVolunteerStatusAndBranch(volunteerStatus, branch, VolunteerPublicInfoProjection.class);
    }



    public void checkConstraintViolations(Volunteer volunteer) {

        CheckConstraintService.checkConstraintViolations(volunteer, Volunteer.class);
    }

    public List<Volunteer> getAllNormal() {
        return volunteerRepo.getAllNormal(Volunteer.class);
    }
    public void setNewKPI(Volunteer volunteer, VolunteerKPI kpi) {
        volunteer.setVolunteerKPI(kpi);
        volunteerRepo.save(volunteer);
    }
}
