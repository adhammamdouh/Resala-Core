package org.resala.Service.Volunteer;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.*;
import org.resala.Models.Address.Capital;
import org.resala.Models.Auth.Response;
import org.resala.Models.Branch;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.KPI.VolunteerKPI;
import org.resala.Models.Organization;
import org.resala.Models.Volunteer.*;
import org.resala.Pair;
import org.resala.Projections.Volunteer.VolunteerProjection;
import org.resala.Projections.Volunteer.VolunteerPublicInfoProjection;
import org.resala.Repository.Volunteer.VolunteerRepo;
import org.resala.Service.Address.AddressService;
import org.resala.Service.Address.CapitalService;
import org.resala.Service.*;
import org.resala.Service.Call.NetworkTypeService;
import org.resala.Service.Privilege.PrivilegeService;
import org.resala.StaticNames;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class VolunteerService implements CommonCRUDService<VolunteerDTO> {
    @Autowired
    private VolunteerRepo volunteerRepo;
    @Autowired
    private BranchService branchService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private CapitalService capitalService;
    @Autowired
    private PrivilegeService privilegeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserStatusService volunteerStatusService;
    @Autowired
    private AddressService addressService;
    @Autowired
    NetworkTypeService networkTypeService;
    @Autowired
    ShirtService shirtService;
    @Autowired
    UserService userService;
    @Autowired
    UserTypeService userTypeService;

    //@Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    public boolean checkPhoneExist(String phone) {
        return volunteerRepo.existsByPhoneNumberAndOrganization_Id(phone, IssTokenService.getOrganizationId());
    }

    public Volunteer getVolForCreation(VolunteerDTO dto, Role role) {
        dto.checkNull();
        Branch branch = branchService.getById(dto.getBranch().getId());
        Organization organization = organizationService.getById(IssTokenService.getOrganizationId());
        Capital capital = capitalService.getById(dto.getAddress().getCapital().getId());
//        Role role = roleService.getRoleByName(roleName);
        UserStatus volunteerStatus = volunteerStatusService.getByName(StaticNames.activeState);
        Shirt shirt = shirtService.getById(dto.getShirt().getId());
        String phoneNumber = dto.getPhoneNumber();
        if (checkPhoneExist(phoneNumber)) throw new MyEntityFoundBeforeException("Phone Number Found Before");
        Volunteer volunteer = modelMapper().map(dto, Volunteer.class);
        volunteer.setId(0);
        volunteer.setBranch(branch);
        volunteer.setOrganization(organization);
        volunteer.getAddress().setCapital(capital);
        volunteer.setShirt(shirt);
        volunteer.setRole(role);
        volunteer.setVolunteerStatus(volunteerStatus);
        checkConstraintViolations(volunteer);
        addressService.checkConstraintViolations(volunteer.getAddress());
        volunteer.setNetworkType(networkTypeService.getNetworkTypeBasedOnVolunteerNumber(phoneNumber));
        return volunteer;
    }

    @Override
    public ResponseEntity<Object> create(List<VolunteerDTO> dtos) {
        ArrayList<Pair<Integer, String>> failed = new ArrayList<>();
        for (int i = 0; i < dtos.size(); i++) {
            try {
                VolunteerDTO dto = dtos.get(i);
                Volunteer volunteer = getVolForCreation(dto, roleService.getRoleByName(StaticNames.normalVolunteer));
                volunteerRepo.save(volunteer);
            } catch (Exception e) {
                failed.add(new Pair<>(i, e.getMessage()));
            }
        }
        if (failed.size() == 0)
            return ResponseEntity.ok(new Response(StaticNames.addedSuccessfully, HttpStatus.OK.value()));
        else
            return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), failed), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<Object> requestToArchive(VolunteerDTO dto) {
        Volunteer volunteer = getById(dto.getId());
        if (!volunteer.getVolunteerStatus().getName().equals(StaticNames.activeState))
            throw new ActiveStateException("This Volunteer State is " + volunteer.getVolunteerStatus().getName());
        UserStatus volunteerStatus = volunteerStatusService.getByName(StaticNames.requestedToArchiveState);
        volunteer.setVolunteerStatus(volunteerStatus);
        volunteerRepo.save(volunteer);
        return ResponseEntity.ok(new Response("Requested To Archive Successfully", HttpStatus.OK.value()));
    }

    public ResponseEntity<Object> declineToArchive(VolunteerDTO dto) {
        Volunteer volunteer = getById(dto.getId());
        if (!volunteer.getVolunteerStatus().getName().equals(StaticNames.requestedToArchiveState))
            throw new ActiveStateException("This Volunteer State is " + volunteer.getVolunteerStatus().getName());
        UserStatus volunteerStatus = volunteerStatusService.getByName(StaticNames.activeState);
        volunteer.setVolunteerStatus(volunteerStatus);
        volunteerRepo.save(volunteer);
        return ResponseEntity.ok(new Response("Declined to Archive Successfully", HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Object> archive(VolunteerDTO dto) {
        Volunteer volunteer = getById(dto.getId());
        if (!volunteer.getVolunteerStatus().getName().equals(StaticNames.requestedToArchiveState))
            throw new ActiveStateException("This Volunteer State is " + volunteer.getVolunteerStatus().getName());
        UserStatus volunteerStatus = volunteerStatusService.getByName(StaticNames.archivedState);
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
        Shirt shirt = shirtService.getById(newDto.getShirt().getId());
        Volunteer newVolunteer = modelMapper().map(newDto, Volunteer.class);
        newVolunteer.setId(volunteer.getId());
        newVolunteer.setBranch(branch);
        newVolunteer.setOrganization(volunteer.getOrganization());
        newVolunteer.getAddress().setId(volunteer.getAddress().getId());
        newVolunteer.getAddress().setCapital(capital);
        newVolunteer.setRole(volunteer.getRole());
//        newVolunteer.setPrivileges(volunteer.getPrivileges());
        newVolunteer.setShirt(shirt);
        newVolunteer.setVolunteerStatus(volunteer.getVolunteerStatus());
        checkConstraintViolations(newVolunteer);
        addressService.checkConstraintViolations(newVolunteer.getAddress());
        newVolunteer.setNetworkType(networkTypeService.getNetworkTypeBasedOnVolunteerNumber(newVolunteer.getPhoneNumber()));
        volunteerRepo.save(newVolunteer);
        return ResponseEntity.ok(new Response(StaticNames.updatedSuccessfully, HttpStatus.OK.value()));
    }

    public Volunteer getById(int id) {
        Optional<Volunteer> optionalVolunteer = volunteerRepo.findByIdAndOrganization_Id(id, IssTokenService.getOrganizationId());
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
        return volunteerRepo.findAllByOrganization_Id(VolunteerProjection.class, IssTokenService.getOrganizationId());
    }

    public List<VolunteerPublicInfoProjection> getAllPublicInfo() {
        return volunteerRepo.findAllByOrganization_Id(VolunteerPublicInfoProjection.class, IssTokenService.getOrganizationId());
    }


    public List<VolunteerProjection> getAllByState(int stateId) {
        UserStatus volunteerStatus = volunteerStatusService.getById(stateId);
        return volunteerRepo.findAllByVolunteerStatusAndOrganization_Id(volunteerStatus, VolunteerProjection.class, IssTokenService.getOrganizationId());
    }

    public List<VolunteerPublicInfoProjection> getAllPublicInfoByState(int stateId) {
        UserStatus volunteerStatus = volunteerStatusService.getById(stateId);
        return volunteerRepo.findAllByVolunteerStatusAndOrganization_Id(volunteerStatus, VolunteerPublicInfoProjection.class, IssTokenService.getOrganizationId());
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
        Role role = roleService.getRoleByName(StaticNames.normalVolunteer);
        UserStatus userStatus = volunteerStatusService.getByName(StaticNames.activeState);
        return volunteerRepo.findAllByRoleAndBranchAndNetworkTypeAndVolunteerStatusAndOrganization_Id
                (role, branch, networkType, userStatus, IssTokenService.getOrganizationId());

    }

    public Volunteer getVolunteerByPhoneNumber(String phoneNumber) {
        Optional<Volunteer> optionalVolunteer = volunteerRepo.findAllByPhoneNumberAndOrganization_Id(phoneNumber, IssTokenService.getOrganizationId());
        if (optionalVolunteer.isPresent()) {
            return optionalVolunteer.get();
        }
        throw new MyEntityNotFoundException("volunteer " + StaticNames.notFound);
    }


    public List<VolunteerProjection> getVolunteersProjectionByBranch(int branchId) {
        branchService.getById(branchId);
        return volunteerRepo.findByBranch_idAndOrganization_Id(branchId, VolunteerProjection.class, IssTokenService.getOrganizationId());
    }

    public List<VolunteerPublicInfoProjection> getVolunteersPublicInfoByBranch(int branchId) {
        branchService.getById(branchId);
        return volunteerRepo.findByBranch_idAndOrganization_Id(branchId, VolunteerPublicInfoProjection.class, IssTokenService.getOrganizationId());
    }

    public List<VolunteerProjection> getVolunteersByStateAndBranch(int stateId, int branchId) {
        Branch branch = branchService.getById(branchId);
        UserStatus volunteerStatus = volunteerStatusService.getById(stateId);
        return volunteerRepo.findAllByVolunteerStatusAndBranchAndOrganization_Id(volunteerStatus, branch, VolunteerProjection.class, IssTokenService.getOrganizationId());
    }

    public List<VolunteerPublicInfoProjection> getVolunteersPublicInfoByStateAndBranch(int stateId, int branchId) {
        Branch branch = branchService.getById(branchId);
        UserStatus volunteerStatus = volunteerStatusService.getById(stateId);
        return volunteerRepo.findAllByVolunteerStatusAndBranchAndOrganization_Id(volunteerStatus, branch, VolunteerPublicInfoProjection.class, IssTokenService.getOrganizationId());
    }


    public void checkConstraintViolations(Volunteer volunteer) {
        if (volunteer.getGender() != StaticNames.gender.MALE.ordinal() && volunteer.getGender() != StaticNames.gender.FEMALE.ordinal()) {
            throw new ConstraintViolationException("Gender must be male or female");
        }
        CheckConstraintService.checkConstraintViolations(volunteer, Volunteer.class);
    }

    public List<Volunteer> getAllNormal() {
        return volunteerRepo.getAllNormal(Volunteer.class, IssTokenService.getOrganizationId());
    }

    public void setNewKPI(Volunteer volunteer, VolunteerKPI kpi) {
        volunteer.setVolunteerKPI(kpi);
        volunteerRepo.save(volunteer);
    }

    public ResponseEntity<Object> assignVolunteerUser(List<VolunteerDTO> volunteerDTOS) {
        ArrayList<Pair<Integer, String>> failed = new ArrayList<>();
        int count = 0;
        for (VolunteerDTO dto : volunteerDTOS) {
            try {
                if (dto.getUser() == null)
                    throw new NullException("User");
                Volunteer volunteer = getById(dto.getId());
                if (volunteer.getUser() != null)
                    throw new AssignedBeforeException("Volunteer");
                User user = userService.getById(dto.getUser().getId());

                if (user.getUserType() != null)
                    throw new AssignedBeforeException("User");
                UserType userType = userTypeService.getByName(StaticNames.volunteerType);
                user.setUserType(userType);
                volunteer.setUser(user);
                volunteerRepo.save(volunteer);
                count++;
            } catch (Exception e) {
                failed.add(new Pair<>(count, e.getMessage()));
                count++;
            }
        }
        if (failed.size() == 0)
            return ResponseEntity.ok(new Response(StaticNames.assignedSuccessfully, HttpStatus.OK.value()));
        else
            return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), failed), HttpStatus.BAD_REQUEST);
    }

    public Volunteer getVolunteerByUserId(int id) {
        Optional<Volunteer> optional = volunteerRepo.findByUser_id(id);
        if (!optional.isPresent())
            throw new BadCredentialsException("Wrong User Name Or Password");
        return optional.get();
    }

    public void savaVol(Volunteer volunteer) {
        checkConstraintViolations(volunteer);
        volunteerRepo.save(volunteer);
    }
}
